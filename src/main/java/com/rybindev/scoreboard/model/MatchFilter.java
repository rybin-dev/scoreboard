package com.rybindev.scoreboard.model;

import lombok.Data;

@Data
public class MatchFilter {
    private int page;

    private int limit = 5;

    private String playerName;
}
