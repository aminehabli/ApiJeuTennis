package com.bfb.tennis.domain.model;

import java.util.Objects;

/**
 *
 */
public class Score {
     private int pointsPlayerA;
     private int pointsPlayerB;
     private static final String[] SCORE_GAME = {"0","15", "30","40"};

    public Score() {
        this.pointsPlayerA = 0;
        this.pointsPlayerB = 0;
    }


    public void setPointsPlayerA(int pointsPlayerA) {
        this.pointsPlayerA = pointsPlayerA;
    }

    public void setPointsPlayerB(Integer pointsPlayerB) {
        this.pointsPlayerB = pointsPlayerB;
    }

    /**
     * Ajoute le point qunas un joueur gagne le point
     * @param tennisPlayer : joueur
     */
    public void incrementsScore(TennisPlayer tennisPlayer){
        if("A".equals(tennisPlayer.getName())){
            pointsPlayerA++;
        } else {
            pointsPlayerB++;
        }
    }

    /**
     * Teste la règle deuce
     * @return isDeuce
     */
     public boolean isDeuce() {
         return pointsPlayerA >= 3 && pointsPlayerB >= 3 && pointsPlayerA == pointsPlayerB;
    }

    /**
     * Teste la règle Advanatge
     * @return s'il y a un Advanatge
     */
    public boolean hasAdvantage() {
        return pointsPlayerA >= 4 && pointsPlayerA == pointsPlayerB + 1
                || pointsPlayerB >= 4 && pointsPlayerB == pointsPlayerA + 1;
    }

    /**
     * Test s'il y a un gagnant du jeu
     * @return true s'il y a un gagnat sinon false
     */
    public boolean hasWinner() {
        return pointsPlayerA >= 4 && pointsPlayerA >= pointsPlayerB + 2
                || pointsPlayerB >= 4 && pointsPlayerB >= pointsPlayerA + 2;
    }

    /**
     * @return la description du score
     */
    public String getScoreDescription(){
         return new StringBuilder("Player A : ")
                 .append(SCORE_GAME[pointsPlayerA])
                 .append(" / ")
                 .append("Player B : ")
                 .append(SCORE_GAME[pointsPlayerB])
                 .append(System.lineSeparator()).toString();

    }
    /**
     * @param game : Jeu
     * @return retourne le joueur qui méne le jeu
     */
    public TennisPlayer getLeadingGame(Game game){
         return pointsPlayerA > pointsPlayerB ? game.getTennisPlayer1() : game.getTennisPlayer2();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return pointsPlayerA == score.pointsPlayerA && pointsPlayerB == score.pointsPlayerB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointsPlayerA, pointsPlayerB);
    }
}
