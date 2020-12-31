package com.mkowalski.nb.etl.project.repository;

import com.mkowalski.nb.etl.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProjectRepository extends  JpaRepository<Project, Long>, QuerydslPredicateExecutor<Project>, CustomProjectRepository {

}
