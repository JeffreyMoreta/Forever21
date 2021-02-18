package com.games.forever21.blackjack.domain;
import org.junit.Before;
import org.junit.Test;
import com.apps.util.Prompter;
import java.io.File;
import java.util.Scanner;
import static org.junit.Assert.*;
import java.util.*;



public class TableTest {

    private Dealer dealer = (Dealer) Gambler.createGambler("Dealer", "Jay", 100_000);
    private TableTest tableTest;
    private Collection<Gambler> gamblers = new LinkedList<>();
    private Collection<Gambler> gamblersSet = new LinkedList<>();
    private Map<Gambler, Integer>bets = new HashMap<>();


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

        gamblers.add(Gambler.createGambler("Player", "Jeffrey", 1000));
        gamblers.add(Gambler.createGambler("Player", "Amin", 700));
        gamblers.add(Gambler.createGambler("Player", "Danny", 500));
        gamblers.add(Gambler.createGambler("Player", "Ivy", 750));

        bets.put(Gambler.createGambler("Player", "Jeffrey", 1000), 100);
        bets.put(Gambler.createGambler("Player", "Amin", 700), 70);
        bets.put(Gambler.createGambler("Player", "Danny", 500), 50);
        bets.put(Gambler.createGambler("Player", "Ivy", 750), 75);
    }


    @Test
    public void populatePlayerList() throws Exception{
        Prompter prompter = new Prompter(new Scanner(new File("responses/responses.txt")));
        for (int i = 0; i < 4; i++) {
            String name = prompter.prompt(ANSI_YELLOW + "Aloha, please enter your fabulous name: " + ANSI_RESET, "^[a-zA-Z]*$", ANSI_RED + "\n Please enter a valid name." + "\u001B[0m");
            Integer amount = Integer.parseInt(prompter.prompt(ANSI_YELLOW + "Please enter the initial amount: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + "\u001B[0m"));
            gamblers.add(Gambler.createGambler("Player", name, amount));
        }
        assertEquals(gamblersSet, gamblers);
    }

    @Test
    public void showPlayerList(){
        List<Gambler> gamblerList = new ArrayList<>(gamblers);
        List<Gambler> gamblerSet = new ArrayList<>(gamblersSet);
        for (int i=0; i<4; i++){
            assertEquals(gamblerList.get(i).getName(), gamblerSet.get(i).getName());
            assertEquals(gamblerList.get(i).getBalance(), gamblerSet.get(i).getBalance());
        }
    }

    @Test
    public void placingBet() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/bets.txt")));
        for (Gambler gambler : gamblers) {
            Integer bet = Integer.parseInt(prompter.prompt(WHITE_BRIGHT + gambler.getName() + ", Please enter your bet: $" + ANSI_RESET, "\\d+", ANSI_RED + "\n Please enter a valid amount. Ex: 5000" + ANSI_RESET));
            assertEquals(bet, bets.get(gambler));
        }
    }

    @Test
    public void dealTwoCards() {
        List<Gambler> gamblerList = new ArrayList<>(gamblers);
        dealer.dealCard(gamblerList.get(0));
        dealer.dealCard(gamblerList.get(0));
        assertEquals(gamblerList.get(0).getCurrentHand().size(), 2);
    }

    @Test
    public void goAroundTable() {
        //test if we can complete a round
    }

    @Test
    public void whoWonGame() {
        //needs test
    }

}
