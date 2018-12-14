package com.poccofinance.loan.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
class ErrorDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<ConstraintViolation<Serializable>> constraintViolations;
    private LocalDateTime timestamp;
    private String message;
    private String details;

    ErrorDetails(Set<ConstraintViolation<Serializable>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
