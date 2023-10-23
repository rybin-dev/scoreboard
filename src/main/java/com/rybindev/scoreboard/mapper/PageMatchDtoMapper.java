package com.rybindev.scoreboard.mapper;

import com.rybindev.scoreboard.model.PageMatch;
import com.rybindev.scoreboard.model.PageMatchDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PageMatchDtoMapper implements Mapper<PageMatch, PageMatchDto> {
    private final MatchDtoMapper matchDtoMapper;

    @Override
    public PageMatchDto mapFrom(PageMatch object) {
        return new PageMatchDto(
                object.getCount(),
                object.getPage(),
                object.getPageLimit(),
                object.getMatches()
                        .stream()
                        .map(matchDtoMapper::mapFrom)
                        .toList());


    }
}
