package com.games.forever21.blackjack.domain;

import java.util.Collection;

public abstract class Gamblers {
    private String name;
    private int balance;
    private Collection<Card> currentHand;
    private boolean pass;
    private boolean hasLost;
    private Long amountOfWins;

    public void countHand() {

    }

    public void hit() {

    }

    public void pass() {

    }

    public void currentBalance() {

    }
}