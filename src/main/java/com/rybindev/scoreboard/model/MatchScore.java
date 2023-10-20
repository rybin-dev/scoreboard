package com.rybindev.scoreboard.model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MatchScore extends BaseScore {
    @Getter
    private final List<SetScore> setScores = new ArrayList<>();

    @Getter
    private SetScore set = new SetScore();

    @Override
    public void next(EPlayer player) {
        synchronized (this) {
            set.next(player);

            if (!set.isFinish()) {
                return;
            }

            int score = playerScores.get(player);

            if (score == 1) {
                winner = player;
                addPoint(player);
                setScores.add(set);
            } else {
                addPoint(player);
                setScores.add(set);
                set = new SetScore();
            }
        }
    }
}
