package com.games.forever21.blackjack.domain;

import java.util.Collection;

public abstract class Gambler {
    // INSTANCE FACTORY (STATIC)
    public static Gambler createGambler(String type, String name, int balance) throws IllegalArgumentException {
        Gambler gambler;
        if ("Dealer".equalsIgnoreCase(type)) {
            gambler = new Dealer(name);
        } else if ("Player".equalsIgnoreCase(type)) {
            gambler = new Player(name);
        } else {
            throw new IllegalArgumentException("Gambler: Incorrect arguments have been passed inorder to create a gambler." +
                    " Must pass a type, proper name, and a balance. i.e.; (Dealer, Jay, 5000)");
        }
        return gambler;
    }

    // FIELDS
    private String name;
    private int balance;
    private Collection<Card> currentHand;
    private boolean pass = false;
    private boolean hasLost;
    private Long amountOfWins;

    // CONSTRUCTORS
    Gambler(String name) {
        setName(name);
    }

    Gambler(String name, int balance) {
        this(name);
        setBalance(balance);
    }

    // METHODS
    // Will count values of the cards in the gambler's current hand and return an int
    public int countHand() {
        int result = 0;
        for (Card card : currentHand) {
            result += card.getValue();
        }
        return result;
    }

    /*
     * This will add a card to the player hand. This should not be called directly.
     * dealer.dealCard(Gambler gambler) should be called instead. Might have to rename this for clarity.
     */
    public void hit(Card card) {
        currentHand.add(card);
    }

    // This will remove a card from the gambler's hand
    public void removeCard(Card card) {
        currentHand.remove(card);
    }

    // This will turn pass to true. Signalling that this gambler will no longer accept cards and their turn is done.
    public void pass() {
        setPass(true);
    }

    // ACCESSORS
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