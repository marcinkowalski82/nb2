package com.mkowalski.nb.etl.project.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "PROJECT")
@SequenceGenerator(name = "generator", sequenceName = "S_PROJECT", allocationSize = 1)
public class Project {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE")
    private String type;
}
