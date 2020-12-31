package com.mkowalski.nb.etl.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String cron;

    @NotNull
    private Boolean enabled;

    private String className;
}
