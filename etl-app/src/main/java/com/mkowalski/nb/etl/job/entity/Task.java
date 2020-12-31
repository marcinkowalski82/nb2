package com.mkowalski.nb.etl.job.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "PROCESS")
@SequenceGenerator(name = "generator", sequenceName = "S_PROCESS", allocationSize = 1)
public class Task {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAGE_ID")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private TaskType taskType;

    @Column(name = "NAME", nullable = false)
    private String name;
}
