package com.games.forever21.blackjack.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class GamblerTest {
    // FIXTURES
    Gambler player;

    @Before
    public void setUp() {
        player = Gambler.createGambler("Player", "TEST-PLAYER", 10_000);
    }

    @Test
    public void createGambler_shouldReturnTrue_whenPlayerCreated() {
        Gambler playerTest = Gambler.createGambler("Player", "TEST-PLAYER2", 10_000);
        assertTrue(playerTest instanceof Player);
    }

    @Test
    public void createGambler_shouldReturnTrue_whenDealerCreated() {
        Gambler gamblerTest = Gambler.createGambler("Dealer", "TEST-DEALER", 10_000);
        assertTrue(gamblerTest instanceof Dealer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createGambler_shouldThrowException_whenBadType() {
        Gambler gamblerTest = Gambler.createGambler("TEST", "TEST-DEALER", 10_000);
    }

    @Test
    public void countHand_shouldReturnTrue_whenGamblerHasNoCards() {
        assertEquals(0, player.countHand());
    }

    @Test
    public void countHand_shouldReturnTrue_whenGamblerHasOneCard() {
        player.hit(Card.TEN_CLUBS);
        assertEquals(10, player.countHand());
    }

    @Test
    public void countHand_shouldReturnTrue_whenGamblerHasMultipleCards() {
        player.hit(Card.THREE_CLUBS);       // 3
        player.hit(Card.FOUR_SPADES);       // 7
        player.hit(Card.NINE_SPADES);       // 16

        assertEquals(16, player.countHand());
    }

    @Test
    public void countHand_shouldReturnTrue_whenGamblerHasAceAndMultipleCards() {
        player.hit(Card.ACE_CLUBS);         // 11
        player.setAcesInHand(1);            // Make player aware of Ace - required for accurate math

        player.hit(Card.THREE_CLUBS);       // 14
        player.hit(Card.TWO_SPADES);        // 16

        assertEquals(16, player.countHand());
    }

    @Test
    public void countHand_shouldReturnTrue_whenGamblerHasAceAndMultipleCards_whenOver21() {
        player.hit(Card.ACE_HEARTS);        // 11
        player.setAcesInHand(1);            // Make player aware of Ace - required for accurate math

        player.hit(Card.THREE_CLUBS);       // 14
        player.hit(Card.NINE_SPADES);       // 13

        assertEquals(13, player.countHand());
    }

    @Test
    public void hit_shouldReturnTrue_whenGamblerHasNoCards() {
        Collection<Card> testCase = Arrays.asList(Card.ACE_CLUBS);

        player.hit(Card.ACE_CLUBS);

        assertEquals(testCase, player.getCurrentHand());
    }

    @Test
    public void hit_shouldReturnTrue_whenGamblerHasOneCard() {
        Collection<Card> testCase = Arrays.asList(Card.ACE_CLUBS, Card.EIGHT_HEARTS);

        player.hit(Card.ACE_CLUBS);
        player.hit(Card.EIGHT_HEARTS);

        assertEquals(testCase, player.getCurrentHand());
    }

    @Test
    public void hit_shouldReturnTrue_whenGamblerHasMultipleCards() {
        Collection<Card> testCase = Arrays.asList(Card.ACE_CLUBS, Card.EIGHT_HEARTS, Card.TEN_CLUBS);

        player.hit(Card.ACE_CLUBS);
        player.hit(Card.EIGHT_HEARTS);
        player.hit(Card.TEN_CLUBS);

        assertEquals(testCase, player.getCurrentHand());
    }

    @Test
    public void removeCard_shouldReturnTrue_whenGamblerHasNoCards() {
        Collection<Card> testCase = new ArrayList<>();

        player.removeCard(Card.ACE_CLUBS);

        assertEquals(testCase, player.getCurrentHand());
    }

    @Test
    public void removeCard_shouldReturnTrue_whenGamblerHasOneCard() {
        Collection<Card> testCase = new ArrayList<>();

        player.hit(Card.ACE_CLUBS);

        player.removeCard(Card.ACE_CLUBS);

        assertEquals(testCase, player.getCurrentHand());
    }

    @Test
    public void removeCard_shouldReturnTrue_whenGamblerHasMultipleCards() {
        Collection<Card> testCase = Arrays.asList(Card.EIGHT_HEARTS, Card.TEN_CLUBS);

        player.hit(Card.ACE_CLUBS);
        player.hit(Card.EIGHT_HEARTS);
        player.hit(Card.TEN_CLUBS);

        player.removeCard(Card.ACE_CLUBS);

        assertEquals(testCase, player.getCurrentHand());
    }
}