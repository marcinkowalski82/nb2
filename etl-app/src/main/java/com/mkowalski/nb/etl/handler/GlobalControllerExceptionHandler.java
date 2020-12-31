package com.mkowalski.nb.etl.handler;

import com.mkowalski.nb.etl.scheduler.exception.JobNotFoundException;
import com.mkowalski.nb.etl.scheduler.exception.ScheduleJobException;
import com.mkowalski.nb.etl.scheduler.exception.UnScheduleJobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<GlobalErrorsDto> jobNotFound(JobNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new GlobalErrorsDto(new RestErrorDto(ex.getMessage())));
    }

    @ExceptionHandler({ScheduleJobException.class, UnScheduleJobException.class})
    public ResponseEntity<GlobalErrorsDto> schedulingError(JobNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalErrorsDto(new RestErrorDto(ex.getMessage())));
    }
}
