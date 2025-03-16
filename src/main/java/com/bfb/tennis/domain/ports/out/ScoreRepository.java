package com.bfb.tennis.domain.ports.out;


import java.util.List;

public interface ScoreRepository {

     void saveScore(String score);

     List<String> getAllScores();

     String getLatestScore();

     void clearScores();
}
