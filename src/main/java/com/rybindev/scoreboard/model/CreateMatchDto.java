package com.rybindev.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMatchDto {
    private String firstPlayerName;
    private String secondPlayerName;
}
