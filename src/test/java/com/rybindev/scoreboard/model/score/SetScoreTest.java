package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;

import com.rybindev.scoreboard.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetScoreTest {
    private GameScore gameMock;

    @BeforeEach
    void prepare() {
        gameMock = mock(GameScore.class);
    }

    @Test
    void shouldEndWhenThePlayerGets6Points() throws IllegalAccessException {
        doNothing().when(gameMock).next(EPlayer.FIRST);
        doReturn(true).when(gameMock).isFinish();

        SetScore set = new SetScore();

        Field game = field("game");
        game.setAccessible(true);
        game.set(set, gameMock);

        Field playerScores = field("playerScores");
        playerScores.setAccessible(true);
        playerScores.set(set, new HashMap<>() {{
            put(EPlayer.FIRST, 5);
            put(EPlayer.SECOND, 0);
        }});

        set.next(EPlayer.FIRST);

        assertThat(set.isFinish()).isTrue();
        assertThat(set.getWinner()).isEqualTo(EPlayer.FIRST);
    }

    @Test
    void shouldBeATieBreakWhenTheScoreBecomes6_6() throws IllegalAccessException {
        doNothing().when(gameMock).next(EPlayer.FIRST);
        doReturn(true).when(gameMock).isFinish();

        SetScore set = new SetScore();

        Field game = field("game");
        game.setAccessible(true);
        game.set(set, gameMock);

        Field playerScores = field("playerScores");
        playerScores.setAccessible(true);
        playerScores.set(set, new HashMap<>() {{
            put(EPlayer.FIRST, 5);
            put(EPlayer.SECOND, 6);
        }});


        set.next(EPlayer.FIRST);


        assertThat(set.getGame().getClass()).isEqualTo(TieBreakScore.class);
    }

    private Field field(String fieldName) {
        return TestUtils.getField(SetScore.class, fieldName);
    }

}