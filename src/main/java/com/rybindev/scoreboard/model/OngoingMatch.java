package com.rybindev.scoreboard.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class OngoingMatch {
    private UUID uuid = UUID.randomUUID();
    private Map<EPlayer, String> playerNames = new HashMap<>(2);
    private MatchScore matchScore = new MatchScore();


    public EPlayer getWinner() {
        return matchScore.getWinner();
    }

    public String getName(EPlayer player) {
        return playerNames.get(player);
    }

    public void setName(EPlayer player, String name) {
        playerNames.put(player, name);
    }

    public boolean isFinish() {
        return matchScore.isFinish();
    }
}
