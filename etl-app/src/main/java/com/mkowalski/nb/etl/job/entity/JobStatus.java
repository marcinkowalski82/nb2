package com.mkowalski.nb.etl.job.entity;

import com.mkowalski.nb.etl.scheduler.entity.ExecutionStatus;
import com.mkowalski.nb.etl.scheduler.entity.Job;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "JOB_STATUS")
@SequenceGenerator(name = "generator", sequenceName = "S_JOB_STATUS", allocationSize = 1)
public class JobStatus {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @OneToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @Column(name = "END_DATE")
    private LocalDate startDate;

    @Column(name = "START_DATE")
    private LocalDate endDate;

    @Column(name = "EXECUTION_STATUS")
    private ExecutionStatus executionStatus;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;
}
