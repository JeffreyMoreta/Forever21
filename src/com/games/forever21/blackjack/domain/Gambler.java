package com.games.forever21.blackjack.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * This base class generates both Players and Dealers. It keeps track of the individual's hand and balance.
 */
public abstract class Gambler {
    // INSTANCE FACTORY (STATIC)
    public static Gambler createGambler(String type, String name, int balance) throws IllegalArgumentException {
        Gambler gambler;
        if ("Dealer".equalsIgnoreCase(type)) {
            gambler = new Dealer(name, balance);
        } else if ("Player".equalsIgnoreCase(type)) {
            gambler = new Player(name, balance);
        } else {
            throw new IllegalArgumentException("Gambler: Incorrect arguments have been passed inorder to create a gambler." +
                    " Must pass a type, proper name, and a balance. i.e.; (Dealer, Jay, 5000)");
        }
        return gambler;
    }

    // FIELDS
    private String name;
    private int balance;
    private Collection<Card> currentHand = new ArrayList<>();
    private boolean pass = false;
    private boolean hasWon;
    private int acesInHand;

    // CONSTRUCTORS
    Gambler(String name, int balance) {
        setName(name);
        setBalance(balance);
    }

    // METHODS
    /**
     * Will count values of the cards in the gambler's current hand and return an int
     */
    public int countHand() {
        int result = 0;
        int acesInHand = getAcesInHand();

        for (Card card : currentHand) {
            result += card.getValue();
        }

        while (acesInHand > 0 && result > 21) {
            result -= 10;
            acesInHand--;
        }

        return result;
    }

    /**
     * This will add a card to the player hand. This should not be called directly.
     *  dealer.dealCard(Gambler gambler) should be called instead.
     */
    public void hit(Card card) {
        currentHand.add(card);
    }

    /**
     * This will remove a card from the gambler's hand
     */
    public void removeCard(Card card) {
        currentHand.remove(card);
    }

    /**
     * This will turn pass to true. Signalling that this gambler will no longer accept cards and their turn is done.
     */
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

    public boolean hasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public int getAcesInHand() {
        return acesInHand;
    }

    public void setAcesInHand(int acesInHand) {
        this.acesInHand = acesInHand;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Gambler gambler = (Gambler) other;
        return getBalance() == gambler.getBalance() &&
                pass == gambler.pass &&
                hasWon == gambler.hasWon &&
                getAcesInHand() == gambler.getAcesInHand() &&
                getName().equals(gambler.getName()) &&
                getCurrentHand().equals(gambler.getCurrentHand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBalance(), getCurrentHand(), pass, hasWon, getAcesInHand());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", currentHand=" + currentHand +
                ", pass=" + pass +
                ", hasWon=" + hasWon +
                ", acesInHand=" + acesInHand +
                ']';
    }
}