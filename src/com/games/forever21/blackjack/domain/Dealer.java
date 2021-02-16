package com.games.forever21.blackjack.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Random;

class Dealer extends Gambler {
    // FIELDS
    private Collection<Card> deck = EnumSet.allOf(Card.class);
    private Random randomGenerator = new Random();

    // CONSTRUCTORS
    Dealer(String name) {
        super(name);
    }

    Dealer(String name, int balance) {
        super(name, balance);
    }

    // METHODS
    // Will grab a random card from the deck
    Card getRandomCard() {
        Card result = null;
        int randomNumber = randomGenerator.nextInt(deck.size());
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

    // Will remove the specified card from the deck
    void removeCardFromDeck(Card card) {
        deck.remove(card);
    }


    // Will pass a card to the specified gambler
    void dealCard(Gambler gambler) {
        Card randomCard = getRandomCard();
        gambler.hit(randomCard);
        removeCardFromDeck(randomCard);
    }

    // Will grab the cards of all Gamblers passed in a Collection.
    Collection<Card> grabCardsFromTable(Collection<Gambler> gamblers) {
        Collection<Card> gamblerCards = new ArrayList<>();

        for (Gambler gambler : gamblers) {
            for(Card card : gambler.getCurrentHand()) {
                gamblerCards.add(card);
                gambler.removeCard(card);
            }
        }
        return gamblerCards;
    }

    // Will go through all the player hands and add those cards back to the deck
    void recoverCards(Collection<Gambler> gamblers) throws IllegalArgumentException {
        Collection<Card> gamblerCards = grabCardsFromTable(gamblers);

        if(gamblerCards.size() == 0 || gamblerCards == null) {
            throw new IllegalArgumentException("Dealer: Input is invalid. Please pass in a Collection of Gamblers.");
        } else {
            deck.addAll(gamblerCards);
        }
    }
}