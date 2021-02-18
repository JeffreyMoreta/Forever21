package com.games.forever21.blackjack.client;

import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.games.forever21.blackjack.controller.Table;

import java.io.IOException;
import java.util.Scanner;

class Main implements SplashApp{

    @Override
    public void start(){
        Table table = new Table(new Prompter(new Scanner(System.in)));
        try {
            table.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main1 = new Main();
        //main1.welcome("images/picture.jpeg");
        main1.start();
    }
}