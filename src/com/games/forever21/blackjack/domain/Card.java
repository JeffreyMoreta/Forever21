package com.games.forever21.blackjack.domain;

/**
 * This enum is in charge of creating once instance of every card that will belong to the deck.
 */
public enum Card {
    ACE_DIAMONDS(11),  TWO_DIAMONDS(2),  THREE_DIAMONDS(3), FOUR_DIAMONDS(4),
    ACE_CLUBS(11),     TWO_CLUBS(2),     THREE_CLUBS(3),    FOUR_CLUBS(4),
    ACE_HEARTS(11),    TWO_HEARTS(2),    THREE_HEARTS(3),   FOUR_HEARTS(4),
    ACE_SPADES(11),    TWO_SPADES(2),    THREE_SPADES(3),   FOUR_SPADES(4),

    FIVE_DIAMONDS(5),  SIX_DIAMONDS(6),  SEVEN_DIAMONDS(7), EIGHT_HEARTS(8),
    FIVE_CLUBS(5),     SIX_CLUBS(6),     SEVEN_CLUBS(7),    EIGHT_DIAMONDS(8),
    FIVE_HEARTS(5),    SIX_HEARTS(6),    SEVEN_HEARTS(7),   EIGHT_CLUBS(8),
    FIVE_SPADES(5),    SIX_SPADES(6),    SEVEN_SPADES(7),   EIGHT_SPADES(8),

    NINE_SPADES(9),    TEN_SPADES(10),   JACK_SPADES(10),   QUEEN_SPADES(10),
    NINE_DIAMONDS(9),  TEN_DIAMONDS(10), JACK_DIAMONDS(10), QUEEN_DIAMONDS(10),
    NINE_CLUBS(9),     TEN_CLUBS(10),    JACK_CLUBS(10),    QUEEN_CLUBS(10),
    NINE_HEARTS(9),    TEN_HEARTS(10),   JACK_HEARTS(10),   QUEEN_HEARTS(10),

    KING_SPADES(10),
    KING_DIAMONDS(10),
    KING_CLUBS(10),
    KING_HEARTS(10);

    // FIELDS
    private int value;

    // CONSTRUCTORS
    Card(int value) {
        this.value = value;
    }




    // METHODS
    // ACCESSORS
    public int getValue() {
        return value;
    }
}