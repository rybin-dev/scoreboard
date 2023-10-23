package com.rybindev.scoreboard.service;

import com.rybindev.scoreboard.mapper.PageMatchDtoMapper;
import com.rybindev.scoreboard.model.MatchFilter;
import com.rybindev.scoreboard.model.PageMatchDto;
import com.rybindev.scoreboard.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final PageMatchDtoMapper pageMatchDtoMapper;



    @Transactional
    public PageMatchDto findAll(MatchFilter filter) {
        return pageMatchDtoMapper.mapFrom(matchRepository.findAll(filter));
    }

}
