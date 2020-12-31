package com.mkowalski.nb.etl.scheduler.repository;

import com.mkowalski.nb.etl.scheduler.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface JobRepository extends  JpaRepository<Job, Long>,
                                              QuerydslPredicateExecutor<Job>,
                                              CustomJobRepository {
}
