package com.games.forever21.blackjack.domain;
import com.games.forever21.blackjack.controller.Table;
import com.games.forever21.blackjack.domain.DisplayHand;
import com.games.forever21.blackjack.domain.Gambler;
import com.games.forever21.blackjack.domain.Dealer;
import com.games.forever21.blackjack.domain.Player;
import org.junit.Before;
import org.junit.Test;
import com.apps.util.Prompter;

import java.util.Scanner;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

import static com.games.forever21.blackjack.controller.Table.ANSI_YELLOW;

public class TableTest {

    private TableTest tableTest;
    private Collection<Gambler> gamblers = new LinkedList<>();

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
    }

    @Test
    public void testShowBanner() {

    }

    @Test
    public void populatePlayerList() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        Collection<Gambler> gamblersSet = new LinkedList<>();
        gamblersSet.add(new Player("Jeffrey", 1000));
        gamblersSet.add(new Player("Amin", 700));
        gamblersSet.add(new Player("Danny", 500));
        gamblersSet.add(new Player("Ivy", 750));

        for (int i = 0; i < 4; i++) {
            String name = prompter.prompt(ANSI_YELLOW + "Aloha, please enter your fabulous name: " + ANSI_RESET, "^[a-zA-Z]*$", ANSI_RED + "\n Please enter a valid name." + "\u001B[0m");
            int amount = Integer.parseInt(prompter.prompt(ANSI_YELLOW + "Please enter the initial amount: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + "\u001B[0m"));
            System.out.println(ANSI_YELLOW + "Welcome to the game: " + ANSI_RESET + GREEN_BOLD_BRIGHT + name + ANSI_RESET + ANSI_YELLOW + ". Your balance is: " + ANSI_RESET + GREEN_BOLD_BRIGHT + GREEN_UNDERLINED + amount + ANSI_RESET + "." + ANSI_RESET);
            System.out.println();
            gamblers.add(Gambler.createGambler("Player", name, amount));
        }

        assertEquals(gamblersSet, gamblers);
    }

    @Test
    public void showPlayerList() {
    }

    @Test
    public void placingBet() {
    }

    @Test
    public void dealTwoCards() {
    }

    @Test
    public void showPlayersHands() {
    }

    @Test
    public void goAroundTable() {
    }

    @Test
    public void whoWonGame() {
    }

    @Test
    public void endGame() {
    }
}
