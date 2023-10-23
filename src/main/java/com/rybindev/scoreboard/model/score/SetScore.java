package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;
import lombok.Getter;

public class SetScore extends BaseScore {
    @Getter
    private BaseScore game = new GameScore();

    private boolean tieBreak;

    @Override
    public void next(EPlayer player) {
        game.next(player);

        if (!game.isFinish()) {
            return;
        }

        int score = playerScores.get(player);
        int oppositeScore = playerScores.get(player.getOpposite());


        if (score == 5 && oppositeScore == 6) {
            addPoint(player);
            game = new TieBreakScore();
            tieBreak = true;
        } else if (((score == 5 || score == 6) && oppositeScore < score) || tieBreak) {
            winner = player;
            addPoint(player);
        } else {
            addPoint(player);
            game = new GameScore();
        }

    }


}
