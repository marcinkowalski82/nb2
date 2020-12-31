package com.mkowalski.nb.etl.scheduler.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleJobException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Error while scheduling job";
    }
}
