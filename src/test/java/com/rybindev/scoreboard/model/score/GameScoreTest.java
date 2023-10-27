package com.rybindev.scoreboard.model.score;

import com.rybindev.scoreboard.model.EPlayer;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameScoreTest {

    @Test
    void shouldNotEndWhenTheScoreIs3_3() {
        GameScore game = new GameScore();

        for (int i = 0; i < 3; i++) {
            assertThat(game.isFinish()).isFalse();
            game.next(EPlayer.FIRST);
            game.next(EPlayer.SECOND);
        }
        assertThat(game.isFinish()).isFalse();
    }
    @Test
    void shouldNotEdWithTheScore3_4() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            assertThat(game.isFinish()).isFalse();
            if (i < 3) {
                game.next(EPlayer.FIRST);
            }
            game.next(EPlayer.SECOND);
        }

        assertThat(game.isFinish()).isFalse();
    }

    @Test
    void shouldNotEdWithTheScore4_3() {
        GameScore game = new GameScore();

        for (int i = 0; i < 4; i++) {
            assertThat(game.isFinish()).isFalse();
            game.next(EPlayer.FIRST);
            if (i < 3) {
                game.next(EPlayer.SECOND);
            }

        }

        assertThat(game.isFinish()).isFalse();
    }

    @Test
    void shouldEndWhenThePlayerGets4Points() {
        GameScore game1 = new GameScore();
        GameScore game2 = new GameScore();

        for (int i = 0; i < 4; i++) {
            assertThat(game1.isFinish()).isFalse();
            assertThat(game2.isFinish()).isFalse();
            game1.next(EPlayer.FIRST);
            game2.next(EPlayer.SECOND);
        }

        assertThat(game1.isFinish()).isTrue();
        assertThat(game2.isFinish()).isTrue();
    }




}