package com.mkowalski.nb.etl.scheduler;

import com.mkowalski.nb.etl.scheduler.dto.JobDto;
import com.mkowalski.nb.etl.scheduler.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchedulerController {
    Page<JobDto> retrieve(Pageable pageable, SearchDto searchDto);
   void update(JobDto schedulerDto);
}
