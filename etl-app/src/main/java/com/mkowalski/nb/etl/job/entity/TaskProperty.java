package com.mkowalski.nb.etl.job.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Data
@Entity
@Table(name = "TASK_PROPERTY")
@SequenceGenerator(name = "generator", sequenceName = "S_TASK_PROPERTY", allocationSize = 1)
public class TaskProperty {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    private Task taskId;

    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "VALUE")
    private String value;
}
