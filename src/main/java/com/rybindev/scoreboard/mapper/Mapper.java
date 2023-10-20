package com.rybindev.scoreboard.mapper;

public interface Mapper<F,T> {
    T mapFrom(F object);
}
