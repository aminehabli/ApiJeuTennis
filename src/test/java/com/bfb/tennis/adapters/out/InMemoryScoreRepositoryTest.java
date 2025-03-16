package com.bfb.tennis.adapters.out;

import com.bfb.tennis.domain.ports.out.ScoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryScoreRepositoryTest {

    ScoreRepository scoreRepository;

    @BeforeEach
    void setUp() {
        scoreRepository = new InMemoryScoreRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ShouldGetAllScoreswhenCallToGetAllScores() {
        scoreRepository.saveScore("test");
        List<String> allScores = scoreRepository.getAllScores();
        assertThat(allScores).isEqualTo(List.of("test"));

    }

    @Test
    void ShouldGetLatestScoreWhenCallSaveScore() {
        String expectedMessageScore = "test";
        scoreRepository.saveScore("test");
        String latestScore = scoreRepository.getLatestScore();
        assertThat(latestScore).isEqualTo(expectedMessageScore);
    }

    @Test
    void shouldGetNullWhenCallGetLatestScoreWithoutAnySavingScore() {
        String latestScore = scoreRepository.getLatestScore();
        assertThat(latestScore).isEqualTo(null);
    }

    @Test
    void ShouldClearAllScores() {
        scoreRepository.saveScore("test1");
        scoreRepository.saveScore("test2");
        scoreRepository.clearScores();
        List<String> allScores = scoreRepository.getAllScores();
        assertTrue(allScores.isEmpty());
    }
}