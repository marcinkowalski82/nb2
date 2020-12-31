package com.mkowalski.nb.etl.scheduler.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnScheduleJobException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Error while unscheduling job";
    }
}
