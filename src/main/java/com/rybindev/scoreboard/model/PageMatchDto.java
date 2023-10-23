package com.rybindev.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class PageMatchDto {
    private Long count;
    private Integer page;
    private Integer pageLimit;
    private List<MatchDto> matches;

    public Integer getCountPage(){
        return (int) Math.ceil(((double) count) / pageLimit);
    }
}
