package com.rybindev.scoreboard.mapper;

import com.rybindev.scoreboard.entity.Match;
import com.rybindev.scoreboard.model.MatchDto;

public class MatchDtoMapper implements Mapper<Match, MatchDto> {
    @Override
    public MatchDto mapFrom(Match object) {
        MatchDto.Player first = new MatchDto.Player(
                object.getFirst().getId().equals(object.getWinner().getId()),
                object.getFirst().getName());

        MatchDto.Player second = new MatchDto.Player(
                object.getSecond().getId().equals(object.getWinner().getId()),
                object.getSecond().getName());

        return new MatchDto(first, second);
    }
}
