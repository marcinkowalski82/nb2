package com.mkowalski.nb.etl.scheduler.repository;

import com.mkowalski.nb.etl.scheduler.entity.Job;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJobRepository {
    Page<Job> fetchPaged(Pageable pageRequest);
    Page<Job> fetchPaged(Pageable pageRequest, Predicate predicate);
}
