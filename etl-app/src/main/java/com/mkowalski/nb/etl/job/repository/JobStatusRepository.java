package com.mkowalski.nb.etl.job.repository;

import com.mkowalski.nb.etl.job.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobStatusRepository extends  JpaRepository<JobStatus, Long> {

}
