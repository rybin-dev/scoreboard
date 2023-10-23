package com.rybindev.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Scoreboard {
    private String[] names;
    private List<String[]> setScores;
    private String[] gameScore;
    private boolean finish;
}
