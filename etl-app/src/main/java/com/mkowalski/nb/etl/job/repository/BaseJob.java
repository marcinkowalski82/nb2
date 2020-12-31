package com.mkowalski.nb.etl.job.repository;

import com.mkowalski.nb.etl.scheduler.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
public class BaseJob implements Job {

    private final JobRepository jobRepository;
    private final JobStatusRepository jobStatusRepository;

    public BaseJob(JobRepository jobRepository,
                   JobStatusRepository jobStatusRepository) {
        this.jobRepository = jobRepository;
        this.jobStatusRepository = jobStatusRepository;
    }

    public void start() {

    }

    public void end() {

    }

    public void updateStatus() {

    }

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Job ** {} ** starting @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        log.info(jobRepository.findAll().get(0).toString());
        log.info("Job ** {} ** completed.  Next job scheduled @ {}", context.getJobDetail().getKey().getName(), context.getNextFireTime());
    }

}
