package com.mkowalski.nb.etl.project;

import com.mkowalski.nb.etl.project.dto.ProjectDto;
import com.mkowalski.nb.etl.project.dto.ProjectSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Page<ProjectDto> retrieve(Pageable pageable, ProjectSearchDto searchDto);
}
