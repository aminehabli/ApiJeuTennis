package com.bfb.tennis.application;

import com.bfb.tennis.domain.model.Game;
import com.bfb.tennis.domain.model.TennisPlayer;
import com.bfb.tennis.domain.ports.in.GameService;
import com.bfb.tennis.domain.ports.out.ScoreRepository;

import java.util.function.BooleanSupplier;
import java.util.regex.Pattern;


public class GameServiceImpl implements GameService {

    private final ScoreRepository scoreRepository;

    private Game currentGame;

    public GameServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * Ajouter le point et enregistrer le score
     * @param winnerOfPoint : A ou B
     */
    @Override
    public void pointWonBy(Character winnerOfPoint) {

         checkValidation(winnerOfPoint);

        if ('A' == winnerOfPoint) {
            currentGame.pointWonBy(currentGame.getTennisPlayer1());
        } else if ('B' == winnerOfPoint){
            currentGame.pointWonBy(currentGame.getTennisPlayer2());
        }
      scoreRepository.saveScore(currentGame.getScore());
    }

    /**
     * Lancer un nouveau jeu
     * @param tennisPlayer1 : joueur 1
     * @param tennisPlayer2 : joueur 2
     */
    @Override
    public void startNewGame(TennisPlayer tennisPlayer1, TennisPlayer tennisPlayer2) {
         currentGame = new Game(tennisPlayer1, tennisPlayer2);
    }

    /**
     * @return : Retourne le nom di gagnant
     */
    @Override
    public String getWinnerName() {
        return currentGame.getWinnerName();
    }

    /**
     * valider les input
     * @param input : match le pattern ^[AB]*$
     */
    private void checkValidation(Character input){
       validate(() -> null == input, "input is null");
       validate(() -> !Pattern.matches("^[AB]*$", input + ""), "input is not valid");
    }

    /**
     * throw IllegalArgumentException si l'input n'est pas valide
     * @param supplier : BooleanSupplier
     * @param message : String
     */
    private void validate(BooleanSupplier supplier, String message) {
        if (supplier.getAsBoolean())
            throw new IllegalArgumentException(message);
    }
}
