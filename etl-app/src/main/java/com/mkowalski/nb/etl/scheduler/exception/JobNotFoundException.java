package com.mkowalski.nb.etl.scheduler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Job not found";
    }
}
