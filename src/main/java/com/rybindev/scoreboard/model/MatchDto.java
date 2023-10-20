package com.rybindev.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchDto {
    private Player first;
    private Player second;

    @Data
    @AllArgsConstructor
    public static class Player {
        private boolean winner;
        private String name;
    }
}
