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

    public Gamblers createGambler(String type, String name) {
        Gamblers gambler;

        if ("Dealer".equalsIgnoreCase(type)){
            gambler = new Dealer(name);
        } else {
            gambler = new Player(name);
        }

        return gambler;
    }

    public Gamblers createGambler(String type, String name, int balance) {
        Gamblers gambler;

        if ("Dealer".equalsIgnoreCase(type)){
            gambler = new Dealer(name, balance);
        } else {
            gambler = new Player(name, balance);
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