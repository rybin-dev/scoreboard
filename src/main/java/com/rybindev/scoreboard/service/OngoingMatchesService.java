package com.rybindev.scoreboard.service;


import com.rybindev.scoreboard.entity.Player;
import com.rybindev.scoreboard.exception.OngoingMatchNotFoundException;
import com.rybindev.scoreboard.mapper.MatchMapper;
import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.repository.MatchRepository;
import com.rybindev.scoreboard.repository.OngoingMatchesRepository;
import com.rybindev.scoreboard.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class OngoingMatchesService {

    private final OngoingMatchesRepository ongoingMatchesRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Transactional
    public OngoingMatch createMath(String name1, String name2) {
        Player first = playerRepository
                .findByName(name1)
                .orElseGet(() -> playerRepository.save(new Player(name1)));
        Player second = playerRepository
                .findByName(name2)
                .orElseGet(() -> playerRepository.save(new Player(name2)));

        OngoingMatch ongoingMatch = new OngoingMatch();
        ongoingMatch.setName(EPlayer.FIRST, name1);
        ongoingMatch.setName(EPlayer.SECOND, name2);

        ongoingMatchesRepository.save(ongoingMatch);

        return ongoingMatch;
    }

    public OngoingMatch next(UUID uuid, EPlayer player) {
        OngoingMatch ongoingMatch = ongoingMatchesRepository
                .find(uuid)
                .orElseThrow(OngoingMatchNotFoundException::new);

        if (!ongoingMatch.isFinish()) {
            ongoingMatch.getMatchScore().next(player);
        } else {
            matchRepository.save(matchMapper.mapFrom(ongoingMatch));
            ongoingMatchesRepository.delete(uuid);
        }

        return ongoingMatch;
    }

    public OngoingMatch findByUuid(UUID uuid) {

        return ongoingMatchesRepository.find(uuid).orElseThrow();
    }



}
