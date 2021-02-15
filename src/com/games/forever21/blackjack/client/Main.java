package com.games.forever21.blackjack.client;

import com.apps.util.Prompter;
import com.games.forever21.blackjack.controller.Table;

import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Table table= new Table(new Prompter(new Scanner(System.in)));
        try {
            table.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}