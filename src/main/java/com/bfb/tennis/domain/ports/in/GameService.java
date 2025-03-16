package com.bfb.tennis.domain.ports.in;

import com.bfb.tennis.domain.model.TennisPlayer;

public interface GameService {

    void pointWonBy(Character winnerOfPoint);

    void startNewGame(TennisPlayer tennisPlayer1, TennisPlayer tennisPlayer2);

    String getWinnerName();
}
