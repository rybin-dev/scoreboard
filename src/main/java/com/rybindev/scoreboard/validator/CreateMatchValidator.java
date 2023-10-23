package com.rybindev.scoreboard.validator;

import com.rybindev.scoreboard.model.CreateMatchDto;

public class CreateMatchValidator implements Validator<CreateMatchDto> {
    private static final String PATTERN_NAME = "[A-Za-zА-яЁё]+";

    @Override
    public ValidationResult isValid(CreateMatchDto object) {
        ValidationResult validationResult = new ValidationResult();
        String first = object.getFirstPlayerName();
        String second = object.getSecondPlayerName();
        if (isValidName(first)) {
            validationResult.add(Error.of("invalid.player.name", "First player name is not valid"));
        }
        if (isValidName(second)) {
            validationResult.add(Error.of("invalid.player.name", "Second player name is not valid"));
        }
        if (first != null && first.equals(second)){
            validationResult.add(Error.of("invalid.duplicate,name", "Duplicate name"));
        }

        return validationResult;
    }

    private boolean isValidName(String name) {
        return name == null || !name.matches(PATTERN_NAME);
    }
}
