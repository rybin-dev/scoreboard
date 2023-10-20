package com.rybindev.scoreboard.mapper;

import com.rybindev.scoreboard.model.BaseScore;
import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.model.Scoreboard;

import java.util.List;

public class ScoreboardMapper implements Mapper<OngoingMatch, Scoreboard> {

    @Override
    public Scoreboard mapFrom(OngoingMatch object) {
        String[] names = {
                object.getName(EPlayer.FIRST),
                object.getName(EPlayer.SECOND)};

        List<String[]> setScores = object.getMatchScore()
                .getSetScores()
                .stream()
                .map(e -> new String[]{
                        e.getScoreStrung(EPlayer.FIRST),
                        e.getScoreStrung(EPlayer.SECOND)
                }).toList();

        String[] currentSetScore = {
                object.getMatchScore().getSet().getScoreStrung(EPlayer.FIRST),
                object.getMatchScore().getSet().getScoreStrung(EPlayer.SECOND)};

        BaseScore game = object.getMatchScore().getSet().getGame();
        String[] gameScore = {
                game.getScoreStrung(EPlayer.FIRST),
                game.getScoreStrung(EPlayer.SECOND)};

        return new Scoreboard(names, setScores, gameScore, currentSetScore, object.isFinish());
    }
}
