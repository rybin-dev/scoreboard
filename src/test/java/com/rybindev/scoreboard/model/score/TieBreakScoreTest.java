package com.rybindev.scoreboard.model.score;


import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class TieBreakScoreTest {

    @Test
    void shouldBeATieBreakWhenTheScoreBecomes6_6() throws IllegalAccessException {
        TieBreakScore tieBreak = new TieBreakScore();
        Field playerScores = field("playerScores");
        playerScores.setAccessible(true);
        playerScores.set(tieBreak,new HashMap<>(){{put(EPlayer.FIRST,6); put(EPlayer.SECOND,5);}});


        tieBreak.next(EPlayer.FIRST);


        assertThat(tieBreak.isFinish()).isTrue();
        assertThat(tieBreak.getWinner()).isEqualTo(EPlayer.FIRST);
    }

    private Field field(String fieldName) {
        return TestUtils.getField(TieBreakScore.class, fieldName);
    }
}