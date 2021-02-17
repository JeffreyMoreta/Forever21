package com.games.forever21.blackjack.domain;

import java.util.*;

// might make this an enum
public class DisplayHand {
    // METHODS
    private static List<List<String>> goThroughCards(Collection<Card> hand) {
        List<List<String>> cards = new LinkedList<>();
        for (Card card : hand) {
            cards.add(processCard(card));
        }
        return cards;
    }

    private static List<String> processCard(Card card) {
        String[] cardSplit = card.name().split("_");
        String number = readNumber(cardSplit[0]);
        String suite = readSuite(cardSplit[1]);

        return template(number, suite);
    }

    private static String readNumber(String number) {
        String result = "";
        switch (number) {
            case "ACE" :
                result = "A";
                break;
            case "ONE" :
                result = "1";
                break;
            case "TWO" :
                result = "2";
                break;
            case "THREE" :
                result = "3";
                break;
            case "FOUR" :
                result = "4";
                break;
            case "FIVE" :
                result = "5";
                break;
            case "SIX" :
                result = "6";
                break;
            case "SEVEN" :
                result = "7";
                break;
            case "EIGHT" :
                result = "8";
                break;
            case "NINE" :
                result = "9";
                break;
            case "TEN" :
                result = "10";
                break;
            case "JACK" :
                result = "J";
                break;
            case "QUEEN" :
                result = "Q";
                break;
            case "KING" :
                result = "K";
                break;
        }
        return result;
    }

    private static String readSuite(String suite) {
        String result = "";
        switch(suite) {
            case "SPADES":
                result = "♣";
                break;
            case "DIAMONDS":
                result = "♦";
                break;
            case "CLUBS":
                result = "♠";
                break;
            case "HEARTS":
                result = "♥";
                break;
        }
        return result;
    }

    private static List<String> template(String number, String suite) {
        List<String> playingCard = new LinkedList<>();

        playingCard.add("┌─────────┐");
        playingCard.add("│"+ number +"      │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│    " + suite + "  │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│      "+ number +"│");
        playingCard.add("└─────────┘");

        return playingCard;
    }

    // Might have to change this to print through the prompter instead of directly to System.out
    public static void printHand(Collection<Card> hand){
        List<List<String>> cards = goThroughCards(hand);

        // This is looping through the amount of rows that our card template is made of. In this case it is 9.
        // TODO: Don't hardcode the number 9 in there. Change this.
        for (int i = 0; i < 9; i++) {
            StringBuilder combinedRows = new StringBuilder();
            // We are now looping through each card and grabbing a row on each loop. This allows us to
            //  print the entirety of someone's hand horizontally instead of vertically.
            for (List<String> card : cards) {
                combinedRows.append(card.get(i));
            }
            System.out.println(combinedRows);
        }
    }
}
