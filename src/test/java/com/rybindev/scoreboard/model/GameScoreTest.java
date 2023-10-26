package com.rybindev.scoreboard.model;

import com.rybindev.scoreboard.model.score.GameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameScoreTest {

    @Test
    void shouldNotEndWhenTheScoreIs3_3() {
        GameScore game = new GameScore();

        for (int i = 0; i < 3; i++) {
            Assertions.assertFalse(game.isFinish());
            game.next(EPlayer.FIRST);
            game.next(EPlayer.SECOND);

        }
        Assertions.assertFalse(game.isFinish());
    }
    @Test
    void shouldNotEdWithTheScore3_4() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            Assertions.assertFalse(game.isFinish());
            if (i < 3) {
                game.next(EPlayer.FIRST);
            }
            game.next(EPlayer.SECOND);

        }
        Assertions.assertFalse(game.isFinish());
    }

    @Test
    void shouldNotEdWithTheScore4_3() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            Assertions.assertFalse(game.isFinish());
            if (i < 3) {
                game.next(EPlayer.SECOND);
            }
            game.next(EPlayer.FIRST);

        }
        Assertions.assertFalse(game.isFinish());
    }
    @Test
    void shouldEndWhenThePlayerGets4Points() {
        GameScore game1 = new GameScore();
        GameScore game2 = new GameScore();

        for (int i = 0; i < 4; i++) {
            Assertions.assertFalse(game1.isFinish());
            Assertions.assertFalse(game2.isFinish());
            game1.next(EPlayer.FIRST);
            game2.next(EPlayer.SECOND);

        }

        Assertions.assertTrue(game1.isFinish());
        Assertions.assertTrue(game2.isFinish());
    }




}