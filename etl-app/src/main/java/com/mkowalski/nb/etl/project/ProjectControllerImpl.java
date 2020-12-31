package com.mkowalski.nb.etl.project;

import com.mkowalski.nb.etl.project.dto.ProjectDto;
import com.mkowalski.nb.etl.project.dto.ProjectSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/project")
@Api(tags = {"Scheduler API"})
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService projectService;

    public ProjectControllerImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ApiOperation(value = "Get projects", response = ProjectDto.class, responseContainer = "Page")
    public Page<ProjectDto> retrieve(@ApiParam(value = "Paging criteria")  @Valid Pageable pageable,
                                     @ApiParam(value = "Job search criteria") ProjectSearchDto searchDto) {
        return projectService.retrieve(pageable, searchDto);
    }
}
