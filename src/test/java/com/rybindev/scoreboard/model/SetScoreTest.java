package com.rybindev.scoreboard.model;

import com.rybindev.scoreboard.model.score.SetScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SetScoreTest {

    @Test
    void shouldWinTheFirstPlayer() {
        SetScore set = new SetScore();

        for (int i = 0; i < 24; i++) {
            Assertions.assertFalse(set.isFinish());
            set.next(EPlayer.FIRST);
        }

        Assertions.assertTrue(set.isFinish());
        Assertions.assertEquals(EPlayer.FIRST,set.getWinner());
    }
}