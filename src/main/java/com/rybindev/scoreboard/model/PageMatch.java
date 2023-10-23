package com.rybindev.scoreboard.model;

import com.rybindev.scoreboard.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageMatch {
    private Long count;
    private Integer page;
    private Integer pageLimit;
    private List<Match> matches;
}
