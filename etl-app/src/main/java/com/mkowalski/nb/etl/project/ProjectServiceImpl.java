package com.mkowalski.nb.etl.project;

import com.mkowalski.nb.etl.project.dto.ProjectDto;
import com.mkowalski.nb.etl.project.dto.ProjectSearchDto;
import com.mkowalski.nb.etl.project.entity.Project;
import com.mkowalski.nb.etl.project.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Page<ProjectDto> retrieve(Pageable pageable, ProjectSearchDto searchDto) {
        Page<Project> projects = projectRepository.fetchPaged(pageable, searchDto.toQueryDslPredicate());
        return new PageImpl<>(ProjectMapper.INSTANCE.toDto(projects.getContent()),
                pageable, projects.getTotalElements());
    }
}
