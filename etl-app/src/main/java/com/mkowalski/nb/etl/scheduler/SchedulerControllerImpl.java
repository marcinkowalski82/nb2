package com.mkowalski.nb.etl.scheduler;

import com.mkowalski.nb.etl.scheduler.dto.JobDto;
import com.mkowalski.nb.etl.scheduler.dto.SearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/scheduler")
@Api(tags = {"Scheduler API"})
public class SchedulerControllerImpl implements SchedulerController{

    private final SchedulerService schedulerService;

    public SchedulerControllerImpl(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping
    @ApiOperation(value = "Get scheduled jobs", response = JobDto.class, responseContainer = "Page")
    public Page<JobDto> retrieve(@ApiParam(value = "Paging criteria")  @Valid Pageable pageable,
                                 @ApiParam(value = "Job search criteria") SearchDto searchDto) {
        return schedulerService.retrieve(pageable, searchDto);
    }

    @PutMapping
    @ApiOperation(value = "Update scheduled job")
    public void update(@RequestBody JobDto schedulerDto) {
        schedulerService.update(schedulerDto);
    }
}
