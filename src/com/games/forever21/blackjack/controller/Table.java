package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Gambler;
import com.games.forever21.blackjack.domain.Dealer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Table {

    //FIELDS
    private Prompter prompter;
    private Collection<Gambler> gamblers = new ArrayList<>();
    private Dealer dealer = new Dealer("Jay", 100_000);


    //CONSTRUCTORS
    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    //METHODS
    private void addDealer() {
        gamblers.add(dealer);
    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/bannerFinal.txt"));
        prompter.info(banner);
    }

    //Mother of all the game methods
    public void startGame() throws IOException {
        showBanner();
        populatePlayerList();
        showPlayerList();
        dealTwoCards();
        placingBet();
    }

    //populating map with entries by users(4)
    public void populatePlayerList() {
        for (int i = 0; i < 4; i++) {
            String name = prompter.prompt("Aloha, please enter your fabulous name: ");
            int amount = Integer.parseInt(prompter.prompt("Please the $$$ amount: $", "\\d+", "\n Please enter a valid amount. Ex: 5000.0"));
            System.out.println("Welcome to the game: " + name + ". Your balance is: " + amount + ".");
            System.out.println();
            gamblers.add(Gambler.createGambler("Player", name, amount));
        }
        //ask if ready to play
        Pattern pattern1 = Pattern.compile("[YyNn]", Pattern.CASE_INSENSITIVE);
        String retryText1 = "\n Invalid input. Valid inputs are: [Y] or [y] or [N] or [n].";
        prompter.prompt("Are you ready? ", String.valueOf(pattern1), retryText1);
    }

    //showing the players list
    public void showPlayerList() {
        System.out.println("Players List:");
        for (Gambler gambler : gamblers) {
            System.out.println(gambler.getName() + " : " + gambler.getBalance());
        }
    }

    //dealer passes each player 2 cards including him/her self
    public void dealTwoCards() {
        for(Gambler gambler : gamblers) {
            while(gambler.getCurrentHand().size()<2){
                dealer.dealCard(gambler);
            }
        }
    }

    public void placingBet(){
        //show player hands
        for(Gambler gambler : gamblers) {
            System.out.println(gambler.getCurrentHand());
        }

        //placing bet
        //add them to a map (gambler, Integer)
        //
        for(Gambler gambler : gamblers) {
            int bet = Integer.parseInt(prompter.prompt("Please enter your bet: $", "\\d+", "\n Please enter a valid amount. Ex: 5000.0"));

        }

        //hit or stand

    }


        // 4. for loop to see if players want more cards or not
        // 5. Some will lose here => update balances
        // 6. Some will win,



    public void endGame() {
    }
}