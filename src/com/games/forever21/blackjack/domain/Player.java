package com.games.forever21.blackjack.domain;

public class Player extends Gambler {
    // CONSTRUCTORS
    Player(String name, int balance) {
        super(name, balance);
    }

    // METHODS
    /**
     * Allows the player to make a bet. It removes the amount from their balance.
     */
    public Integer bet(int value) {
        setBalance(getBalance() - value);
        return value;
    }
}