package com.mkowalski.nb.etl.scheduler.entity;

import com.querydsl.core.types.Predicate;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.Optional;
import java.util.function.Function;

import static com.mkowalski.nb.etl.scheduler.entity.QJob.job;

@Data
@Entity
@Table(name = "JOB")
@SequenceGenerator(name = "generator", sequenceName = "S_JOB", allocationSize = 1)
public class Job {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CRON")
    private String cron;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    public static Predicate nameLike(String search) {
        return Optional.ofNullable(search)
                .map(toLike)
                .map(job.name::like)
                .orElse(null);
    }

    public static Predicate cronLike(String search) {
        return Optional.ofNullable(search)
                .map(toLike)
                .map(job.cron::like)
                .orElse(null);
    }

    public static Predicate enabledEquals(String search) {
        return Optional.ofNullable(search)
                .map(toBooleanEquals)
                .map(job.enabled::eq)
                .orElse(null);
    }

    static Function<String,String> toLike = t-> "%"+ t + "%";

    static Function<String,Boolean> toBooleanEquals = t-> Boolean.valueOf(t);
}
