package com.rybindev.scoreboard.model;

import com.rybindev.scoreboard.model.score.GameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameScoreTest {

    @Test
    void shouldWinTheFirstPlayer() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            Assertions.assertFalse(game.isFinish());
            game.next(EPlayer.FIRST);

        }

        Assertions.assertTrue(game.isFinish());
        Assertions.assertEquals(EPlayer.FIRST,game.getWinner());
    }

    @Test
    void shouldWinTheSecondPlayer() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            Assertions.assertFalse(game.isFinish());
            game.next(EPlayer.SECOND);
        }

        Assertions.assertTrue(game.isFinish());
        Assertions.assertEquals(EPlayer.SECOND,game.getWinner());
    }


}