package com.bfb.tennis.domain.model;

import java.util.Objects;

public class Game {

    public static final String NO_WINNER = "Pas de gagnant";

    private final TennisPlayer tennisPlayer1;

    private final TennisPlayer tennisPlayer2;

    private final StringBuilder scoreMessage;

    private final Score score;


    public Game(TennisPlayer tennisPlayer1, TennisPlayer tennisPlayer2) {
        this.tennisPlayer1 = tennisPlayer1;
        this.tennisPlayer2 = tennisPlayer2;
        this.score = new Score();
        this.scoreMessage = new StringBuilder();
    }

    public TennisPlayer getTennisPlayer1() {
        return tennisPlayer1;
    }


    public TennisPlayer getTennisPlayer2() {
        return tennisPlayer2;
    }

    /**
     * Retourne le score
     * @return : String
     */
    public String getScore() {

        if(score.isDeuce()){

            score.setPointsPlayerA(3);
            score.setPointsPlayerB(3);
            return this.scoreMessage.append(score.getScoreDescription()).toString();

        } else if (score.hasAdvantage()) {

                  return  this.scoreMessage
                          .append("Player ")
                          .append(score.getLeadingGame(this).getName())
                          .append( " advantage")
                          .append(System.lineSeparator()).toString();

        } else if (score.hasWinner()) {

            return this.scoreMessage.append("Player ")
                    .append(score.getLeadingGame(this).getName() )
                    .append(" wins")
                    .append(System.lineSeparator()).toString();

        } else {
            return this.scoreMessage.append(score.getScoreDescription()).toString();
        }

    }

    /**
     * Ajoute le point au gagnant
     * @param tennisPlayer : TennisPlayer
     */
    public void pointWonBy(TennisPlayer tennisPlayer){
        score.incrementsScore(tennisPlayer);

    }

    /**
     * Retourne le nom du gagnant sinon No_WINNER
     * @return : String
     */
    public String getWinnerName() {
        if (score.hasWinner()) {
            return score.getLeadingGame(this).getName();
        }
        return NO_WINNER;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(tennisPlayer1, game.tennisPlayer1) && Objects.equals(tennisPlayer2, game.tennisPlayer2) && Objects.equals(score, game.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tennisPlayer1, tennisPlayer2, score);
    }

}
