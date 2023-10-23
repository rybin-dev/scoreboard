package com.rybindev.scoreboard.exception;

import com.rybindev.scoreboard.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errors;
    public ValidationException(List<Error> errors) {
        super();
        this.errors = errors;
    }
}
