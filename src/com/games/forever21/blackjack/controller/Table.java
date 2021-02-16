package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Gamblers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Table {

    private Prompter prompter;
    private Gamblers gamblers;

    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    public void startGame() throws IOException {
        showBanner();
        System.out.println();

        String name = prompter.prompt("Aloha, please enter your fabulous name: ");
        double amount = Integer.parseInt(prompter.prompt("Please the $$$ amount: $", "\\d+", "\n Please enter a valid amount. Ex: 5000.0"));
        System.out.println("Welcome to the game: " + name + ". Your balance is: " + amount + ".");

        System.out.println();

        //ask if ready to play
        Pattern pattern1 = Pattern.compile("[YyNn]", Pattern.CASE_INSENSITIVE);
        String retryText1 = new String("\n Invalid input. Valid inputs are: [Y] or [y] or [N] or [n].");

        String name2 = prompter.prompt("Are you ready? ", String.valueOf(pattern1), retryText1);
    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/bannerFinal.txt"));
        prompter.info(banner);
    }

    public void endGame() {
    }
}