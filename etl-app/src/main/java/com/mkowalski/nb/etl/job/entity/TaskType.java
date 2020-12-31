package com.mkowalski.nb.etl.job.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Data
@Entity
@Table(name = "TASK_TYPE")
@SequenceGenerator(name = "generator", sequenceName = "S_TASK_TYPE", allocationSize = 1)
public class TaskType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String username;

    @Column(name = "TASK_CLASS", nullable = false)
    private String taskClass;
}
