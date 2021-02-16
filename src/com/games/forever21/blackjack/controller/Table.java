package com.games.forever21.blackjack.controller;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.domain.Gambler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Table {

    private Prompter prompter;
    private Gambler gamblers;


    public Table(Prompter prompter){
        this.prompter = prompter;
    }

    public void startGame() throws IOException {
        showBanner();
        String name = prompter.prompt("Please enter name: ");
        System.out.println("Welcome to the game: " + name );


        // ask for name 2
        // create gambler object
        // likewise
        // add it to map

        //

    }

    private void showBanner() throws IOException {
        String banner = Files.readString(Path.of("resources/banner.txt"));
        prompter.info(banner);
    }

    public void endGame() {
    }
}