package com.bfb.tennis.adapters.in;

import com.bfb.tennis.adapters.out.InMemoryScoreRepository;
import com.bfb.tennis.application.GameServiceImpl;
import com.bfb.tennis.domain.model.TennisPlayer;
import com.bfb.tennis.domain.ports.in.GameService;
import com.bfb.tennis.domain.ports.out.ScoreRepository;

import java.util.Scanner;

public class ConsoleAdapter {

    public static final String NO_WINNER = "Pas de gagnant";

    private final GameService gameService;

    private final Scanner scanner;

    private static ScoreRepository scoreRepository;

    public ConsoleAdapter(GameService gameService) {
        this.gameService = gameService;
        this.scanner = new Scanner(System.in);
    }

    public void start(){

        System.out.println("Bienvenue au jeu Roland-Garros!");
        TennisPlayer playerA = new TennisPlayer('A');
        TennisPlayer playerB = new TennisPlayer('B');
        gameService.startNewGame(playerA, playerB);
        while (NO_WINNER.equals(gameService.getWinnerName())){
            System.out.println("Qui a gagné le point A/B ?");
            gameService.pointWonBy(scanner.next().charAt(0));
            System.out.println("Le Score est : " + scoreRepository.getLatestScore());
        }
        System.out.println("Le gagant est : " + gameService.getWinnerName());
    }

    public static void main(String[] args) {
        scoreRepository = new InMemoryScoreRepository();
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(new GameServiceImpl(scoreRepository));
        consoleAdapter.start();
    }


}
