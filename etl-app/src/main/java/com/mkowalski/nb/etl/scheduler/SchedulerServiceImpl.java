package com.mkowalski.nb.etl.scheduler;

import com.mkowalski.nb.etl.scheduler.dto.JobDto;
import com.mkowalski.nb.etl.scheduler.dto.SearchDto;
import com.mkowalski.nb.etl.scheduler.entity.Job;
import com.mkowalski.nb.etl.scheduler.exception.JobNotFoundException;
import com.mkowalski.nb.etl.scheduler.exception.ScheduleJobException;
import com.mkowalski.nb.etl.scheduler.exception.UnScheduleJobException;
import com.mkowalski.nb.etl.scheduler.repository.JobRepository;
import com.querydsl.core.types.ExpressionUtils;
import liquibase.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.quartz.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import com.querydsl.core.types.Predicate;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final JobRepository jobRepository;
    private final Scheduler scheduler;

    public SchedulerServiceImpl(JobRepository jobRepository, Scheduler scheduler) {
        this.jobRepository = jobRepository;
        this.scheduler = scheduler;
    }

    @PostConstruct
    @Override
    public void scheduleAll() {
        jobRepository.findAll()
                .stream()
                .filter(Job::getEnabled)
                .forEach(this::scheduleJob);
    }

    @Override
    public Page<JobDto> retrieve(Pageable pageable, SearchDto searchDto) {

        Page<Job> jobPage = shouldFilter(searchDto) ? fetchPageFiltered(pageable, searchDto.getSearch())
                                                    : fetchPageNotFiltered(pageable);

        return new PageImpl<>(SchedulerMapper.INSTANCE.toDto(jobPage.getContent()),
                              pageable, jobPage.getTotalElements());
    }

    @Override
    public void update(JobDto jobDto) {

        final Job oldJob = jobRepository.findById(jobDto.getId()).orElseThrow(JobNotFoundException::new);
        Job newJob = SchedulerMapper.INSTANCE.toEntity(jobDto);

        newJob.setClassName(oldJob.getClassName());

        if(shouldSchedule(oldJob, newJob)) {
            scheduleJob(newJob);
        }

        if(shouldUnSchedule(oldJob, newJob)) {
            unScheduleJob(oldJob);
        }

        if(shouldReSchedule(oldJob, newJob)) {
            rescheduleJob(newJob);
        }

        jobRepository.save(newJob);
    }

    void scheduleJob(Job Job) {
        try {
            log.info("Scheduling job {} for class {}", Job.getName(), Job.getClassName());
            final JobDetail jobDetail = newJob((Class<? extends org.quartz.Job>) Class.forName(Job.getClassName()))
                                  .withIdentity(createJobKey(Job))
                                  .build();

            CronTrigger cronTrigger = newTrigger()
                                     .withIdentity(createTriggerKey(Job))
                                     .withSchedule(CronScheduleBuilder.cronSchedule(Job.getCron()))
                                     .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.error("Error while scheduling job {}:{}", Job.getId(), e.getMessage());
            throw new ScheduleJobException();
        }
    }

    void unScheduleJob(Job Job) {
        try {
            TriggerKey triggerKey = new TriggerKey(createTriggerKey(Job));
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            log.error("Error while unscheduling job {}:{}", Job.getName(), e.getMessage());
            throw new UnScheduleJobException();
        }
    }

    void rescheduleJob(Job Job) {
        try {
            TriggerKey oldTriggerKey = new TriggerKey(createTriggerKey(Job));
            scheduler.rescheduleJob(oldTriggerKey, createCronTrigger(Job));
        } catch (SchedulerException e) {
            log.error("Error while rescheduling job {}:{}", Job.getName(), e.getMessage());
            throw new ScheduleJobException();
        }
    }

    private CronTrigger createCronTrigger(Job Job) {
        return newTrigger()
                .withIdentity(createTriggerKey(Job))
                .withSchedule(CronScheduleBuilder.cronSchedule(Job.getCron()))
                .build();
    }

    private String createJobKey(Job Job) {
        return "job - " + Job.getId();
    }

    private String createTriggerKey(Job Job) {
        return "trigger - " + Job.getId();
    }

    private boolean shouldSchedule(Job oldJob, Job newJob) {
        return !oldJob.getEnabled() && newJob.getEnabled();
    }

    private boolean shouldUnSchedule(Job oldJob, Job newJob) {
        return oldJob.getEnabled() && !newJob.getEnabled();
    }

    private boolean shouldReSchedule(Job oldJob, Job newJob) {
        return oldJob.getEnabled() && newJob.getEnabled();
    }

    private boolean shouldFilter(SearchDto searchDto) {
        return !StringUtils.isEmpty(searchDto.getSearch());
    }

    private Page<Job> fetchPageFiltered(Pageable pageable, String filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(Job.nameLike(filter));
        predicates.add(Job.cronLike(filter));

        if(Boolean.TRUE.equals(BooleanUtils.toBooleanObject(filter)) ||
           Boolean.FALSE.equals(BooleanUtils.toBooleanObject(filter))) {
           predicates.add(Job.enabledEquals(filter));
        }

        return jobRepository.fetchPaged(pageable, ExpressionUtils.anyOf((predicates)));
    }

    private Page<Job> fetchPageNotFiltered(Pageable pageable) {
        return jobRepository.fetchPaged(pageable);
    }
}