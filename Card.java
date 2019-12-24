package com.poker;

import java.util.Objects;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    static Card fromString(String strCard) {
        char[] chars = strCard.toCharArray();
        Card card = new Card(Rank.symbolToRank(chars[0]), Suit.charToSuit(chars[1]));
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank.getSymbol() + "" + suit.getSymbol();
    }

    public static void main(String[] args) {
        String s = "4D";
        Card card = Card.fromString(s);
        System.out.println("Card is " + card);




    }
}
