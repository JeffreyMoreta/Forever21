package com.games.forever21.blackjack.domain;

class Player extends Gamblers{

    Player(String name) {
        super(name);
    }

    Player(String name, int balance) {
        super(name, balance);
    }

    @Override
    public void countHand() {

    }

    @Override
    public void hit() {

    }

    @Override
    public void pass() {

    }
}