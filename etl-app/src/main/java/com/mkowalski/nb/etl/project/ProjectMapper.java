
package com.mkowalski.nb.etl.project;

import com.mkowalski.nb.etl.project.dto.ProjectDto;
import com.mkowalski.nb.etl.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project job);
    List<ProjectDto> toDto(List<Project> jobs);
    Project toEntity(ProjectDto jobDto);
    List<Project> toEntity(List<ProjectDto> jobs);
}

