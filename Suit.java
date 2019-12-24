package com.poker;

public enum Suit {
    SPADES('S'),
    HEARTS('H'),
    CLUBS('C'),
    DIAMONDS('D');

    private char symbol;

    Suit(char c) {
        symbol = c;
    }

    public char getSymbol() {
        return symbol;
    }

    static Suit charToSuit(char c){
        Suit suit = null;
        for (Suit s: Suit.values()){
            if(c == s.symbol) {
                suit = s;
            }
        }
        if (suit != null) {
            return suit;
        } else {
            throw new IllegalArgumentException("Card suit with symbol " + c + "doesn't exist");
        }
    }


    public static void main(String[] args) {
        for (Suit s : Suit.values()) {
            System.out.println(s + ":" + s.symbol);
        }
        System.out.println(charToSuit('H'));
    }

}







