package com.games.forever21.blackjack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardStructure {
    
    public CardStructure(){
        ArrayList<String> playingCard = new ArrayList<>();
        playingCard.add("┌─────────┐");
        playingCard.add("│{0}      │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│    {1}  │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│      {0}│");
        playingCard.add("└─────────┘");

        List<String> suitName = Arrays.asList("Spade", "King", "Queen", "Heart");
//        suits_symbols = ['♠', '♦', '♥', '♣']

        List<? extends Serializable> cardNumber = Arrays.asList("Ace", 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    // method to read card from player's hand


}
