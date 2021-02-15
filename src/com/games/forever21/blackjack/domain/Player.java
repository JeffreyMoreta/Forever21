package com.games.forever21.blackjack.domain;

class Player extends Gamblers{

    Player(String name) {
        super(name);
    }

    Player(String name, int balance) {
        super(name, balance);
    }

    /**
     * Allows the player to make a bet. It removes the amount from their balance.
     */
    int bet(int value) {
        setBalance(getBalance() - value);
        return value;
    }
}