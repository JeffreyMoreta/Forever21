package com.games.forever21.blackjack.domain;

import java.util.*;

public class Dealer extends Gambler {
    // FIELDS
    private Collection<Card> deck = EnumSet.allOf(Card.class);
    private Random randomGenerator = new Random();

    // CONSTRUCTORS
    public Dealer(String name, int balance) {
        super(name, balance);
    }

    // METHODS
    /**
     * Will grab a random card from the deck. The deck isn't shuffled. randomGenerator will generate a new random
     *  number, from the amount of cards currently in the deck, and that card is pulled from the deck.
     */
    private Card getRandomCard() {
        Card result = null;
        int randomNumber = randomGenerator.nextInt(getDeckSize());
        int index = 0;

        for (Card card : deck) {
            if (index == randomNumber) {
                result = card;
                break;
            }
            index++;
        }

        return result;
    }

    /**
     * Will remove the specified card from the deck
     */
    private void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    /**
     * Will pass a card to the specified gambler also removing it from the deck.
     */
    public void dealCard(Gambler gambler) {
        Card randomCard = getRandomCard();
        gambler.hit(randomCard);
        removeCardFromDeck(randomCard);

        if (randomCard.name().startsWith("ACE")) {
            gambler.setAcesInHand(gambler.getAcesInHand() + 1);
        }
    }

    /**
     * Will grab the cards of all Gamblers passed in a Collection. Removing them from the gambler's hand
     *  and keeps track of the cards by adding them to a collection.
     */
    public Collection<Card> grabCardsFromTable(Collection<Gambler> gamblers) {
        Collection<Card> gamblerCards = new ArrayList<>();

        for (Gambler gambler : gamblers) {
            Collection<Card> cardsToRemove = new ArrayList<>();
            for(Card card : gambler.getCurrentHand()) {
                gamblerCards.add(card);
                cardsToRemove.add(card);
            }

            // Will remove the card from the gambler's hand
            for(Card card : cardsToRemove) {
                gambler.removeCard(card);
            }
        }
        return gamblerCards;
    }

    /**
     * Will go through all the player hands and add those cards back to the deck
     */
    public void recoverCards(Collection<Gambler> gamblers) throws IllegalArgumentException {
        Collection<Card> gamblerCards = grabCardsFromTable(gamblers);

        if(gamblerCards.size() == 0 || gamblerCards == null) {
            throw new IllegalArgumentException("Dealer: Input is invalid. Please pass in a Collection of Gamblers.");
        } else {
            deck.addAll(gamblerCards);
        }
    }

    /**
     * This will grab the value of the dealer's hand and return it as an int
     */
    private int getDealersHand(Collection<Gambler> gamblers) {
        int dealersHand = 0;
        for (Gambler gambler : gamblers) {
            if(gambler instanceof Dealer) {
                dealersHand = gambler.countHand();
            }
        }
        return dealersHand;
    }

    /**
     * This will go through a Collection of Gamblers and find out who won
     */
    public Collection<Gambler> whoWon(Collection<Gambler> gamblers) {
        Collection<Gambler> result = new ArrayList<>();
        int dealersHand = getDealersHand(gamblers);

        for (Gambler gambler : gamblers) {
            if(gambler instanceof Dealer) {
                continue;
            } else {
                int gamblerCurrentHand = gambler.countHand();

                if (gamblerCurrentHand > dealersHand && gamblerCurrentHand <= 21 ||
                        dealersHand > 21 && gamblerCurrentHand <= 21) {
                    gambler.setHasWon(true);
                    result.add(gambler);
                }
            }
        }

        return result;
    }

    /**
     * This will go through the Map of Gambler bets.txt and pay the winners
     */
    public void payOut(Map<Gambler, Integer> bets) {
        for (Map.Entry<Gambler, Integer> bet : bets.entrySet()) {
            Gambler gambler = bet.getKey();
            Integer gamblerBet = bet.getValue();

            if (gambler.hasWon()) {
                gambler.setBalance(getBalance() + (gamblerBet * 2));
                gambler.setHasWon(false);
            }
        }
    }

    // ACCESSORS
    int getDeckSize() {
        return deck.size();
    }

    @Override
    public String toString() {
        return super.toString() + ": " +
                "deck=" + deck +
                ", randomGenerator=" + randomGenerator +
                ']';
    }
}