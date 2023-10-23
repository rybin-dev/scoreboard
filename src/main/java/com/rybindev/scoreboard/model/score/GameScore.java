package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;

public class GameScore extends BaseScore {

    private static final String[] POINTS_GAME = new String[]{"0", "15", "30", "40", "AD"};

    public void next(EPlayer player) {
        int score = playerScores.get(player);
        int oppositeScore = playerScores.get(player.getOpposite());

        if ((score == 3 || score == 4) && oppositeScore < score) {
            winner = player;
        } else if (oppositeScore == 4 && score < oppositeScore) {
            playerScores.put(EPlayer.FIRST, 3);
            playerScores.put(EPlayer.SECOND, 3);
        } else {
            addPoint(player);
        }

    }

    @Override
    public String getScoreStrung(EPlayer player) {
        return POINTS_GAME[this.getScore(player)];
    }
}
