package com.rybindev.scoreboard.mapper;

import com.rybindev.scoreboard.entity.Match;
import com.rybindev.scoreboard.entity.Player;
import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchMapper implements Mapper<OngoingMatch, Match>{
    private final PlayerRepository playerRepository;
    @Override
    public Match mapFrom(OngoingMatch object) {
        Player first = getPlayer(object.getName(EPlayer.FIRST));
        Player second = getPlayer(object.getName(EPlayer.SECOND));
        Player winner = object.getWinner() == EPlayer.FIRST ? first : second;

        Match match = new Match();
        match.setFirst(first);
        match.setSecond(second);
        match.setWinner(winner);

        return match;
    }

    private Player getPlayer(String name) {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
