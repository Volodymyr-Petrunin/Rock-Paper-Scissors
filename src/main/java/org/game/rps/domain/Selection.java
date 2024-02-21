package org.game.rps.domain;

public enum Selection {
    ROCK,
    PAPER,
    SCISSORS;

    public boolean beats(Selection pick) {
        return (this == ROCK && pick == SCISSORS) ||
                (this == SCISSORS && pick == PAPER) ||
                (this == PAPER && pick == ROCK);
    }
}
