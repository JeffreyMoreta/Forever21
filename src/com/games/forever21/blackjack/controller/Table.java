package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Card;
import com.games.forever21.blackjack.domain.Gambler;
import com.games.forever21.blackjack.domain.Dealer;
import com.games.forever21.blackjack.domain.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Table {
    // INSTANCE FIELDS
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    private static final String GREEN_UNDERLINED = "\033[4;32m";
    private static final String WHITE_BRIGHT = "\033[0;97m";
    private static final String YELLOW_BRIGHT = "\033[0;93m";
    private static final String YELLOW_UNDERLINED = "\033[4;33m";

    //FIELDS
    private Prompter prompter;
    private Collection<Gambler> gamblers = new LinkedList<>();
    private Dealer dealer = new Dealer("Jay", 100_000);
    private Map<Gambler, Integer>bets = new HashMap<>();

    //CONSTRUCTORS
    public Table(){
    }

    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    //METHODS
    //Mother of all the game methods
    public void startGame() throws IOException {
        showBanner();
        populatePlayerList();
        addDealer();
        showPlayerList();
        placingBet();
        dealTwoCards();
        showPlayersHands();
        goAroundTable();
        whoWonGame();
        endGame();
    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/bannerFinal.txt"));
        prompter.info(banner);
    }

    private void addDealer() {
        gamblers.add(dealer);
    }

    //populating map with entries by users(1)
    public void populatePlayerList() {

        // asks for how many players are playing
        int startNumber = Integer.parseInt(prompter.prompt(YELLOW_BRIGHT+ "Aloha! \n Welcome to Forever 21 Casino! \n How many people are playing (1-4)? " + ANSI_RESET, "[1-4]", ANSI_RED+ "Please enter a valid number." + ANSI_RESET));

        // Asks for player's name and $ amount
        for (int i = 0; i < startNumber; i++) {
            String name = prompter.prompt(ANSI_YELLOW + "Aloha, please enter your fabulous name: " + ANSI_RESET, "^[a-zA-Z]*$", ANSI_RED + "\n Please enter a valid name." + "\u001B[0m");
            int amount = Integer.parseInt(prompter.prompt(ANSI_YELLOW + "Please enter the initial amount: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + "\u001B[0m"));
            System.out.println(ANSI_YELLOW + "Welcome to the game: " + ANSI_RESET + GREEN_BOLD_BRIGHT + name + ANSI_RESET + ANSI_YELLOW + ". Your balance is: $" + ANSI_RESET + GREEN_BOLD_BRIGHT + amount + ANSI_RESET + "." + ANSI_RESET);
            System.out.println();
            gamblers.add(Gambler.createGambler("Player", name, amount));
        }
        //ask if ready to play
        Pattern pattern1 = Pattern.compile("[YyNn]", Pattern.CASE_INSENSITIVE);
        String retryText1 = "\n Invalid input. Valid inputs are: [Y] or [y] or [N] or [n].";
        prompter.prompt(YELLOW_BRIGHT + "Are you ready? "+ ANSI_RESET, String.valueOf(pattern1), ANSI_RED + retryText1 + ANSI_RESET);

    }

    //showing the players list
    private void showPlayerList() {
        System.out.println("Players List:");
        for (Gambler gambler : gamblers) {
            System.out.println( YELLOW_BRIGHT + gambler.getName()+ ANSI_RESET + GREEN_BOLD_BRIGHT+ " : $" + gambler.getBalance() + ANSI_RESET);
        }
    }

    //dealer passes each player 2 cards including him/her self
    private void dealTwoCards() {
        System.out.println("The dealer is going to give each player 2 cards...");
        for(Gambler gambler : gamblers) {
            while(gambler.getCurrentHand().size()<2){
                dealer.dealCard(gambler);
            }
        }
    }

    private void showPlayersHands(){
        System.out.println("Here are your hands:");
        for(Gambler gambler : gamblers) {
            System.out.println(gambler.getName() + " :");
            DisplayHand.printHand(gambler.getCurrentHand());
            // System.out.println(gambler.getName() + " : " + gambler.getCurrentHand()  + " | " + gambler.countHand());
        }
    }

    //check with Jeffrey again
    private void placingBet(){
        //add them to a map (gambler, Integer)
        System.out.println("Let's " + GREEN_BOLD_BRIGHT+ "bet" + ANSI_RESET + " now!");
        for(Gambler gambler : gamblers) {
            if(gambler instanceof Dealer) continue;
            Player player = (Player) gambler;
            int bet = Integer.parseInt(prompter.prompt(WHITE_BRIGHT + gambler.getName() + ", Please enter your bet: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + ANSI_RESET));
            bets.put(player, player.bet(bet));
        }
    }

    private void goAroundTable(){
        for(Gambler gambler : gamblers) {
            while (!gambler.hasPassed() && gambler.countHand() < 21){
                System.out.println(gambler.getName() + ", With below cards you have " + gambler.countHand());
                DisplayHand.printHand(gambler.getCurrentHand());
                Pattern pattern1 = Pattern.compile("[HhSs]", Pattern.CASE_INSENSITIVE);
                String retryText1 = "\n Invalid input. Valid inputs are: [H] or [h] or [S] or [s].";
                String answer = prompter.prompt(ANSI_YELLOW + gambler.getName() + ", Would you like hit or stand? " + ANSI_RESET, String.valueOf(pattern1), retryText1);
                if(answer.equalsIgnoreCase("h")){
                    dealer.dealCard(gambler);
                    System.out.println("Card dealt");
                }
                else{
                    gambler.pass();
                }
            }
        }
        showPlayersHands();
    }

    public void whoWonGame(){
        System.out.println("Here are the winners: ");
        Collection<Gambler>winners = dealer.whoWon(gamblers);
        for(Gambler winner : winners){
            System.out.println(winner.getName() + ", $" + bets.get(winner));
        }
    }

    // 4. for loop to see if players want more cards or not
    // 5. Some will lose here => update balances
    // 6. Some will win,
    // 7. Loop game until player runs out of money

    public void endGame() {
    }

    // INNER STATIC CLASS
    private static class DisplayHand {
        // INSTANCE FIELD
        private static final int CARD_HEIGHT = 9;       // This is the height of the card template

        // METHODS
        private static List<List<String>> goThroughCards(Collection<Card> hand) {
            List<List<String>> cards = new LinkedList<>();
            for (Card card : hand) {
                cards.add(processCard(card));
            }

            // collects and returns the players hand in ASCII
            return cards;
        }

        private static List<String> processCard(Card card) {
            String[] cardSplit = card.name().split("_");
            String number = readNumber(cardSplit[0]);
            String suite = readSuite(cardSplit[1]);

            // fills in the card template with the number and the suite
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

            playingCard.add("┌───────────┐ ");
            playingCard.add("│ " + number + "         │ ");
            playingCard.add("│           │ ");
            playingCard.add("│           │ ");
            playingCard.add("│     " + suite + "     │ ");
            playingCard.add("│           │ ");
            playingCard.add("│           │ ");
            playingCard.add("│         " + number + " │ ");
            playingCard.add("└───────────┘ ");

            return playingCard;
        }

        // Might have to change this to print through the prompter instead of directly to System.out
        private static void printHand(Collection<Card> hand){
            List<List<String>> cards = goThroughCards(hand);

            // This is looping through the amount of rows that our card template is made of. The max being CARD_HEIGHT.
            for (int i = 0; i < CARD_HEIGHT; i++) {
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
}
