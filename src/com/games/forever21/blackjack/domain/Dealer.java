package com.games.forever21.blackjack.domain;

class Dealer extends Gamblers{

    Dealer(String name) {
        super(name);
    }

    Dealer(String name, int balance) {
        super(name, balance);
    }
}