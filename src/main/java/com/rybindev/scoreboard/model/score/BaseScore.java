package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseScore {
    protected final Map<EPlayer, Integer> playerScores = new HashMap<>() {{
        put(EPlayer.FIRST, 0);
        put(EPlayer.SECOND, 0);
    }};

    protected EPlayer winner;

    public abstract void next(EPlayer player);

    public void addPoint(EPlayer player){
        playerScores.merge(player, 1, Integer::sum);
    }

    public boolean isFinish() {
        return winner != null;
    }

    public EPlayer getWinner() {
        return winner;
    }

    public int getScore(EPlayer player) {
        return playerScores.get(player);
    }

    public String getScoreStrung(EPlayer player) {
        return String.valueOf(this.getScore(player));
    }



}
