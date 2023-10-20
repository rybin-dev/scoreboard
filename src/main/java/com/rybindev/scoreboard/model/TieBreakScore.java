package com.rybindev.scoreboard.model;

public class TieBreakScore extends BaseScore {


    public void next(EPlayer player) {
        int score = playerScores.get(player);
        int oppositeScore = playerScores.get(player.getOpposite());

        if (score >= 6 && oppositeScore < score) {
            winner = player;
            addPoint(player);
        } else {
            addPoint(player);
        }

    }

}
