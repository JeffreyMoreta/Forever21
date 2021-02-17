package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Gambler;
import com.games.forever21.blackjack.domain.Dealer;
import com.games.forever21.blackjack.domain.Player;

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
    private Map<Gambler, Integer>bets;


    //CONSTRUCTORS
    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    //METHODS

    //Mother of all the game methods
    public void startGame() throws IOException {
        showBanner();
        addDealer();
        populatePlayerList();
        showPlayerList();
        dealTwoCards();
        showPlayersHands();
        placingBet();
        goAroundTable();
        endGame();
    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/bannerFinal.txt"));
        prompter.info(banner);
    }

    private void addDealer() {
        gamblers.add(dealer);
    }

    //populating map with entries by users(4)
    public void populatePlayerList() {
        for (int i = 0; i < 4; i++) {
            String name = prompter.prompt("Aloha, please enter your fabulous name: ");
            int amount = Integer.parseInt(prompter.prompt("Please the initial amount: $", "\\d+", "\n Please enter a valid amount. Ex: 5000.0"));
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
    private void showPlayerList() {
        System.out.println("Players List:");
        for (Gambler gambler : gamblers) {
            System.out.println(gambler.getName() + " : " + gambler.getBalance());
        }
    }

    //dealer passes each player 2 cards including him/her self
    private void dealTwoCards() {
        for(Gambler gambler : gamblers) {
            while(gambler.getCurrentHand().size()<2){
                dealer.dealCard(gambler);
            }
        }
    }

    private void showPlayersHands(){
        for(Gambler gambler : gamblers) {
            System.out.println(gambler.getCurrentHand());
        }
    }

    //check with Jeffrey again
    private void placingBet(){
        //add them to a map (gambler, Integer)
        for(Gambler gambler : gamblers) {
            Player player = (Player) gambler;
            int bet = Integer.parseInt(prompter.prompt("Please enter your bet: $", "\\d+", "\n Please enter a valid amount. Ex: 5000"));
            bets.put(player, player.bet(bet));
        }
    }

    private void goAroundTable(){
        for(Gambler gambler : gamblers) {
            Pattern pattern1 = Pattern.compile("[HhSs]", Pattern.CASE_INSENSITIVE);
            String retryText1 = "\n Invalid input. Valid inputs are: [Y] or [y] or [N] or [n].";
            prompter.prompt("Would you like hit or stand? ", String.valueOf(pattern1), retryText1);
            if(pattern1.toString().equals("h") || pattern1.toString().equals("H")){
                dealer.dealCard(gambler);
            }
        }
    }

    // 4. for loop to see if players want more cards or not
    // 5. Some will lose here => update balances
    // 6. Some will win,

    public void endGame() {
    }
}