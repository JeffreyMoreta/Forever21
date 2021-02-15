package com.games.forever21.blackjack.domain;

import java.util.Collection;

public abstract class Gamblers {
    private String name;
    private int balance;
    private Collection<Card> currentHand;
    private boolean pass;
    private boolean hasLost;
    private Long amountOfWins;

    Gamblers(String name) {
        setName(name);
    }

    Gamblers(String name, int balance) {
        this(name);
        setBalance(balance);
    }

    // INSTANCE FACTORY
    Gamblers createGambler(String type, String name) throws IllegalArgumentException {
        Gamblers gambler;
        if ("Dealer".equalsIgnoreCase(type)){
            gambler = new Dealer(name);
        }
        if ("Player".equalsIgnoreCase(type)){
            gambler = new Player(name);
        }
        else{
            throw new IllegalArgumentException("Gambler: Incorrect arguments have been passed inorder to create a gambler." +
                    " Must pass a type and a proper name. i.e.; (Dealer, Jay)");
        }
        return gambler;
    }

    Gamblers createGambler(String type, String name, int balance) throws IllegalArgumentException {
        Gamblers gambler;
        if ("Dealer".equalsIgnoreCase(type)){
            gambler = new Dealer(name);
        }
        if ("Player".equalsIgnoreCase(type)){
            gambler = new Player(name);
        }
        else{
            throw new IllegalArgumentException("Gambler: Incorrect arguments have been passed inorder to create a gambler." +
                    " Must pass a type, proper name, and a balance. i.e.; (Dealer, Jay, 5000)");
        }
        return gambler;
    }

    public void countHand() {

    }

    public void hit(){

    }

    public void pass() {

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

    public void setCurrentHand(Collection<Card> currentHand) {
        this.currentHand = currentHand;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isHasLost() {
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