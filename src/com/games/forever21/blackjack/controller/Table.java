package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Gambler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Table {

    //FIELDS
    private Prompter prompter;
    private Collection<Gambler> gamblers = new ArrayList<>();


    //CONSTRUCTORS
    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    //METHODS
    private void addDealer() {
        Gambler dealer = Gambler.createGambler("Dealer", "Jay", 100_000);
        gamblers.add(dealer);
    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/bannerFinal.txt"));
        prompter.info(banner);
    }

    public void startGame() throws IOException {
        showBanner();
        System.out.println();
        //number of players : 4
        //populating map with entries by users
        for(int i=0; i<4; i++){
            String name = prompter.prompt("Aloha, please enter your fabulous name: ");
            int amount = Integer.parseInt(prompter.prompt("Please the $$$ amount: $", "\\d+", "\n Please enter a valid amount. Ex: 5000.0"));
            System.out.println("Welcome to the game: " + name + ". Your balance is: " + amount + ".");

            System.out.println();

            //ask if ready to play
            Pattern pattern1 = Pattern.compile("[YyNn]", Pattern.CASE_INSENSITIVE);
            String retryText1 = "\n Invalid input. Valid inputs are: [Y] or [y] or [N] or [n].";
            prompter.prompt("Are you ready? ", String.valueOf(pattern1), retryText1);
            Gambler.createGambler("Player", name, amount);
        }
        //showing the players list
        System.out.println("Players List:");
        for(Gambler gambler : gamblers){
            System.out.println(gambler.getName() + " : " + gambler.getBalance());
        }
    }

    public void endGame() {
    }
}