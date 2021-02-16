package com.games.forever21.blackjack.domain;

import junit.framework.TestCase;

import java.util.Set;

public class DealerTest extends TestCase {
    Dealer dealerTest;

    public void setUp() {
        dealerTest = new Dealer("Jay", 50_000);
    }

    public void testGenerateDeck() {
    }

    public void testGetRandomCard() {
        System.out.println(dealerTest.getRandomCard());
    }

    public void testShuffle() {
    }
}