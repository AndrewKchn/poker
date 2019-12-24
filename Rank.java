package com.poker;

public enum  Rank {
    DEUCE('2', 1),
    THREE('3', 2),
    FOUR('4', 3),
    FIVE('5', 4),
    SIX('6', 5),
    SEVEN('7', 6),
    EIGHT('8', 7),
    NINE('9', 8),
    TEN('T', 9),
    JACK('J', 10),
    QUEEN('Q', 11),
    KING('K', 12),
    ACE('A', 13);

    private char symbol;
    private int weight;

    Rank(char c, int w) {
        symbol = c;
        weight = w;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getWeight() {
        return weight;
    }

    static Rank symbolToRank(char c){
        Rank rank = null;
        for (Rank r: Rank.values()){
            if(c == r.symbol) {
                rank = r;
            }
        }
        if (rank != null) {
            return rank;
        } else {
            throw new IllegalArgumentException("Card suit with symbol " + c + "doesn't exist");
        }
    }

    public static void main(String[] args) {
        System.out.println(symbolToRank('T'));
    }
}
