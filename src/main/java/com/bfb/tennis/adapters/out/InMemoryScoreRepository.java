package com.bfb.tennis.adapters.out;

import com.bfb.tennis.domain.ports.out.ScoreRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScoreRepository implements ScoreRepository {

    private final List<String> scores;

    public InMemoryScoreRepository(){
        scores = new ArrayList<>();
    }
    @Override
    public void saveScore(String score) {
        scores.add(score);
    }

    @Override
    public List<String> getAllScores() {
        return new ArrayList<>(scores);
    }

    @Override
    public String getLatestScore() {
        return !scores.isEmpty() ? scores.get(scores.size() - 1) : null;
    }

    @Override
    public void clearScores() {
        scores.clear();
    }
}
