package com.games.forever21.blackjack.domain;
import com.games.forever21.blackjack.controller.Table;
import com.games.forever21.blackjack.domain.Gambler;
import com.games.forever21.blackjack.domain.Dealer;
import com.games.forever21.blackjack.domain.Player;
import org.junit.Before;
import org.junit.Test;
import com.apps.util.Prompter;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;


public class TableTest {

    private TableTest tableTest;
    private Collection<Gambler> gamblers = new LinkedList<>();
    private Collection<Gambler> gamblersSet = new LinkedList<>();


    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    private static final String GREEN_UNDERLINED = "\033[4;32m";
    private static final String WHITE_BRIGHT = "\033[0;97m";
    private static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    private static final String YELLOW_BRIGHT = "\033[0;93m";
    private static final String YELLOW_UNDERLINED = "\033[4;33m";

    @Before
    public void setUp() throws IllegalArgumentException{
        tableTest = new TableTest();
        gamblersSet.add(Gambler.createGambler("Player", "Jeffrey", 1000));
        gamblersSet.add(Gambler.createGambler("Player", "Amin", 700));
        gamblersSet.add(Gambler.createGambler("Player", "Danny", 500));
        gamblersSet.add(Gambler.createGambler("Player", "Ivy", 750));
    }


    @Test
    public void populatePlayerList() throws Exception{
        Prompter prompter = new Prompter(new Scanner(new File("responses/responses.txt")));
        for (int i = 0; i < 4; i++) {
            String name = prompter.prompt(ANSI_YELLOW + "Aloha, please enter your fabulous name: " + ANSI_RESET, "^[a-zA-Z]*$", ANSI_RED + "\n Please enter a valid name." + "\u001B[0m");
            int amount = Integer.parseInt(prompter.prompt(ANSI_YELLOW + "Please enter the initial amount: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + "\u001B[0m"));
            gamblers.add(Gambler.createGambler("Player", name, amount));
        }
        assertEquals(gamblersSet, gamblers);
    }

    @Test
    public void showPlayerList() throws Exception{
        List<Gambler> gamblerList = new ArrayList<>(gamblers);
        System.out.println(gamblerList);
        List<Gambler> gamblerSet = new ArrayList<>(gamblersSet);
        System.out.println(gamblerSet);
        for (int i=0; i<4; i++){
            assertEquals(gamblerList.get(i).getName(), gamblerSet.get(i).getName());
            assertEquals(gamblerList.get(i).getBalance(), gamblerSet.get(i).getBalance());
        }
    }

    @Test
    public void placingBet() {
    }

    @Test
    public void dealTwoCards() {
    }

    @Test
    public void goAroundTable() {
        //test if we can complete a round
    }

    @Test
    public void whoWonGame() {




    }

}
