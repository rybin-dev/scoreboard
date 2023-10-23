package com.rybindev.scoreboard.model.score;


import com.rybindev.scoreboard.model.EPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MatchScore extends BaseScore {
    @Getter
    private final List<SetScore> setScores = new ArrayList<>(3);

    @Getter
    private SetScore set = new SetScore();
    public MatchScore(){
        setScores.add(set);
    }

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
            } else {
                addPoint(player);
                set = new SetScore();
                setScores.add(set);
            }
        }
    }
}
