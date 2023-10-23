package com.rybindev.scoreboard.service;


import com.rybindev.scoreboard.entity.Player;
import com.rybindev.scoreboard.exception.OngoingMatchNotFoundException;
import com.rybindev.scoreboard.exception.ValidationException;
import com.rybindev.scoreboard.mapper.MatchMapper;
import com.rybindev.scoreboard.model.CreateMatchDto;
import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.repository.MatchRepository;
import com.rybindev.scoreboard.repository.OngoingMatchesRepository;
import com.rybindev.scoreboard.repository.PlayerRepository;
import com.rybindev.scoreboard.validator.CreateMatchValidator;
import com.rybindev.scoreboard.validator.ValidationResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class OngoingMatchesService {

    private final OngoingMatchesRepository ongoingMatchesRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final CreateMatchValidator createMatchValidator;

    @Transactional
    public OngoingMatch createMath(CreateMatchDto createMatchDto) {
        ValidationResult validationResult = createMatchValidator.isValid(createMatchDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }

        Player first = playerRepository
                .findByName(createMatchDto.getFirstPlayerName())
                .orElseGet(() -> playerRepository.save(new Player(createMatchDto.getFirstPlayerName())));
        Player second = playerRepository
                .findByName(createMatchDto.getSecondPlayerName())
                .orElseGet(() -> playerRepository.save(new Player(createMatchDto.getSecondPlayerName())));

        OngoingMatch ongoingMatch = new OngoingMatch();
        ongoingMatch.setName(EPlayer.FIRST, createMatchDto.getFirstPlayerName());
        ongoingMatch.setName(EPlayer.SECOND, createMatchDto.getSecondPlayerName());

        ongoingMatchesRepository.save(ongoingMatch);

        return ongoingMatch;
    }

    public OngoingMatch next(UUID uuid, EPlayer player) {
        OngoingMatch ongoingMatch = ongoingMatchesRepository
                .find(uuid)
                .filter(OngoingMatch::isNotFinish)
                .orElseThrow(OngoingMatchNotFoundException::new);

        if (!ongoingMatch.isFinish()) {
            ongoingMatch.getMatchScore().next(player);
        }
        if (ongoingMatch.isFinish()){
            matchRepository.save(matchMapper.mapFrom(ongoingMatch));
            ongoingMatchesRepository.delete(uuid);
        }

        return ongoingMatch;
    }

    public OngoingMatch findByUuid(UUID uuid) {
        return ongoingMatchesRepository.find(uuid).orElseThrow(OngoingMatchNotFoundException::new);
    }

}
