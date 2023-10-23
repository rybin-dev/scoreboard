package com.rybindev.scoreboard.model;

import com.rybindev.scoreboard.model.score.MatchScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatchScoreTest {

    @Test
    void shouldWinTheFirstPlayer() {
        MatchScore match = new MatchScore();

        for (int i = 0; i < 48; i++) {
            Assertions.assertFalse(match.isFinish());
            match.next(EPlayer.FIRST);
        }

        Assertions.assertTrue(match.isFinish());
        Assertions.assertEquals(EPlayer.FIRST,match.getWinner());

    }
}