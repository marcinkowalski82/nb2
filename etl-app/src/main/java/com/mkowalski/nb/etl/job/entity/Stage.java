package com.mkowalski.nb.etl.job.entity;

import com.mkowalski.nb.etl.scheduler.entity.Job;
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
@Table(name = "PROCESS")
@SequenceGenerator(name = "generator", sequenceName = "S_PROCESS", allocationSize = 1)
public class Stage {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROCESS_ID")
    private Job process;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREPROCESSOR_ID")
    private Stage preprocessor;

}
