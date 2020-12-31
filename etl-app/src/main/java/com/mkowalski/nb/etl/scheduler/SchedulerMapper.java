
package com.mkowalski.nb.etl.scheduler;

import com.mkowalski.nb.etl.scheduler.dto.JobDto;
import com.mkowalski.nb.etl.scheduler.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface SchedulerMapper {
    SchedulerMapper INSTANCE = Mappers.getMapper(SchedulerMapper.class);

    JobDto toDto(Job scheduler);
    List<JobDto> toDto(List<Job> scheduler);
    Job toEntity(JobDto jobDto);
    List<Job> toEntity(List<JobDto> jobs);
}

