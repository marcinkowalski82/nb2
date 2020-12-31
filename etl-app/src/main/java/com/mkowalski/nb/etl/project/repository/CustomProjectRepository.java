package com.mkowalski.nb.etl.project.repository;

import com.mkowalski.nb.etl.project.entity.Project;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomProjectRepository {
    Page<Project> fetchPaged(Pageable pageRequest, Predicate predicate);
}
