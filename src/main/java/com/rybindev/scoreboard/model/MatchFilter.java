package com.rybindev.scoreboard.model;

import lombok.Data;

@Data
public class MatchFilter {
    private int page = 1;

    private int limit = 5;

    private String playerName;
}
