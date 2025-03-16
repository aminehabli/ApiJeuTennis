package com.bfb.tennis.domain.model;

import java.util.Objects;

/**
 * TennisPlayer représente le joueur
 */
public class TennisPlayer {

    private final String name;

    public TennisPlayer(Character nameOfPlayer) {
        this.name = nameOfPlayer.toString();
    }

    /**
     * retourne le nom du joueur
     * @return name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TennisPlayer that = (TennisPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
