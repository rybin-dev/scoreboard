package com.rybindev.scoreboard.model;

public enum EPlayer {
    FIRST,
    SECOND;

    public EPlayer getOpposite() {
        return this == FIRST ? SECOND : FIRST;
    }
}
