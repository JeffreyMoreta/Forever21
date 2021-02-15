package com.games.forever21.blackjack.controller;

import com.games.forever21.blackjack.domain.Gamblers;

import java.util.HashMap;
import java.util.Map;

class Table {
    private Gamblers gamblers;

    //map to store gambler and their balances in total
    Map<Gamblers, Integer> gamblersTotalBalance = new HashMap<>();

    //map to store gamblers bets in the current game
    Map<Gamblers, Integer> bets = new HashMap<>();




    public void startGame() {

    }

    public void endGame() {

    }
}