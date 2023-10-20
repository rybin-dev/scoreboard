package com.rybindev.scoreboard.service;

import com.rybindev.scoreboard.mapper.MatchDtoMapper;
import com.rybindev.scoreboard.model.MatchDto;
import com.rybindev.scoreboard.model.MatchFilter;
import com.rybindev.scoreboard.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchDtoMapper matchDtoMapper;

    @Transactional
    public List<MatchDto> findAll(MatchFilter filter) {
        return matchRepository.findAll(filter)
                .stream()
                .map(matchDtoMapper::mapFrom)
                .toList();
    }

    @Transactional
    public List<MatchDto> findAll() {
        return matchRepository.findAll()
                .stream()
                .map(matchDtoMapper::mapFrom)
                .toList();
    }


}
