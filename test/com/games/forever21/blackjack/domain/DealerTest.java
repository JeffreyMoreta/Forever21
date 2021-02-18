package com.games.forever21.blackjack.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DealerTest  {
    // FIXTURES
    Dealer dealer;
    Gambler player;

    @Before
    public void setUp() {
        dealer = (Dealer) Gambler.createGambler("Dealer", "TEST-DEALER", 10_000);
        player = Gambler.createGambler("Player", "TEST-PLAYER", 10_000);
    }

    @Test
    public void dealCard_shouldReturnTrue_whenGamblerHasNoCards() {
        dealer.dealCard(player);
        assertEquals(1, player.getCurrentHand().size());
    }

    @Test
    public void dealCard_shouldReturnTrue_whenGamblerHasOneCard() {
        player.hit(Card.EIGHT_CLUBS);

        dealer.dealCard(player);
        assertEquals(2, player.getCurrentHand().size());
    }

    @Test
    public void dealCard_shouldReturnTrue_whenGamblerHasMultipleCards() {
        player.hit(Card.EIGHT_CLUBS);
        player.hit(Card.TWO_CLUBS);
        player.hit(Card.FOUR_HEARTS);

        dealer.dealCard(player);
        assertEquals(1, player.getCurrentHand().size());
    }

    @Test
    public void grabCardsFromTable_shouldReturnTrue_whenGamblersHaveAHand() {
        Collection<Gambler> testGamblerList = new ArrayList<>();
        Gambler player2 = Gambler.createGambler("Player", "TEST-PLAYER2", 10_000);

        player.hit(Card.FIVE_CLUBS);
        player2.hit(Card.EIGHT_CLUBS);

        testGamblerList.add(player);
        testGamblerList.add(player2);

        Collection<Card> result = dealer.grabCardsFromTable(testGamblerList);
        assertEquals(2, result.size());
    }

    @Test
    public void grabCardsFromTable_shouldReturnTrue_whenGamblersNoHand() {
        Collection<Gambler> testGamblerList = new ArrayList<>();
        Gambler player2 = Gambler.createGambler("Player", "TEST-PLAYER2", 10_000);

        testGamblerList.add(player);
        testGamblerList.add(player2);

        Collection<Card> result = dealer.grabCardsFromTable(testGamblerList);
        assertEquals(0, result.size());
    }

    @Test
    public void recoverCards() {
        Collection<Gambler> testCollection = new ArrayList<>();
        testCollection.add(player);

        for (int i = 0; i < 10; i++) {
            dealer.dealCard(player);
        }

        dealer.recoverCards(testCollection);
        assertEquals(52, dealer.getDeckSize());
    }

    @Test
    public void whoWon_shouldReturnTrue_when() {
        Collection<Gambler> testCollection = new ArrayList<>();
        testCollection.add(player);

        player.hit(Card.ACE_HEARTS);
        player.hit(Card.KING_CLUBS);
        dealer.hit(Card.NINE_CLUBS);

        Collection<Gambler> result = dealer.whoWon(testCollection);

        assertEquals(1, result.size());
    }
}