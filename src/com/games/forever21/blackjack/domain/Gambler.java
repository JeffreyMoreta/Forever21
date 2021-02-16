package com.games.forever21.blackjack.domain;

import java.util.Collection;

public abstract class Gambler {
    // INSTANCE FACTORY
    public static Gambler createGambler(String type, String name, int balance) throws IllegalArgumentException {
        Gambler gambler;
        if ("Dealer".equalsIgnoreCase(type)){
            gambler = new Dealer(name);
        }
        else if ("Player".equalsIgnoreCase(type)){
            gambler = new Player(name);
        }
        else{
            throw new IllegalArgumentException("Gambler: Incorrect arguments have been passed inorder to create a gambler." +
                    " Must pass a type, proper name, and a balance. i.e.; (Dealer, Jay, 5000)");
        }
        return gambler;
    }

    private String name;
    private int balance;
    private Collection<Card> currentHand;
    private boolean pass = false;
    private boolean hasLost;
    private Long amountOfWins;

    Gambler(String name) {
        setName(name);
    }

    Gambler(String name, int balance) {
        this(name);
        setBalance(balance);
    }

    public int countHand() {
        int result = 0;
        for (Card card : currentHand) {
            result += card.getValue();
        }
        return result;
    }

    public void hit(Card card){
        currentHand.add(card);
    }

    public void pass() {
        setPass(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Collection<Card> getCurrentHand() {
        return currentHand;
    }

    public boolean hasPassed() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean hasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public Long getAmountOfWins() {
        return amountOfWins;
    }

    public void setAmountOfWins(Long amountOfWins) {
        this.amountOfWins = amountOfWins;
    }
}