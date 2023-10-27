package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.score.MatchScore;
import com.rybindev.scoreboard.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchScoreTest {

    @Test
    void shouldWinTheFirstPlayer() throws IllegalAccessException {
        SetScore setMock = mock(SetScore.class);
        doNothing().when(setMock).next(EPlayer.FIRST);
        doReturn(true).when(setMock).isFinish();

        MatchScore match = new MatchScore();

        Field playerScores = field("playerScores");
        playerScores.setAccessible(true);
        playerScores.set(match, new HashMap<>() {{
            put(EPlayer.FIRST, 1);
            put(EPlayer.SECOND, 0);
        }});

        Field set = field("set");
        set.setAccessible(true);
        set.set(match, setMock);


        match.next(EPlayer.FIRST);


        assertThat(match.isFinish()).isTrue();
        assertThat(match.getWinner()).isEqualTo(EPlayer.FIRST);
    }

    private Field field(String fieldName) {
        return TestUtils.getField(MatchScore.class, fieldName);
    }
}