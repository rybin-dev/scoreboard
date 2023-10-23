package com.rybindev.scoreboard.validator;

public interface Validator<T>{
   ValidationResult isValid(T object);
}
