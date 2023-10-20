package com.rybindev.scoreboard.repository;

import com.rybindev.scoreboard.model.OngoingMatch;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesRepository {
    Map<UUID, OngoingMatch> matchMap = new ConcurrentHashMap<>();

    public OngoingMatch save(OngoingMatch match) {
        return matchMap.put(match.getUuid(), match);
    }

    public Optional<OngoingMatch> find(UUID uuid) {
        return Optional.ofNullable(matchMap.get(uuid));
    }

    public boolean delete(UUID uuid) {
        return matchMap.remove(uuid) != null;
    }


}
