package com.poker;

import java.util.*;

public class Hand implements Comparable<Hand> {
    private Set<Card> cardSet;

    public Hand(Set<Card> cardSets) {
        if (cardSets.size() != 5) {
            throw new IllegalArgumentException("Card hand should contain exactly 5 unique cards. Cards are: " + cardSet);
        } else this.cardSet = cardSets;
    }

    static Set<Card> fromString(String string) {
        String[] stringCards = string.split(" ");
        Set<Card> cards = new HashSet<>();
        for (String s : stringCards) {
            cards.add(Card.fromString(s));
        }
        return cards;
    }

    private Set<Rank> getUniqueRanks() {
        Set<Rank> uniqueRanks = new HashSet<>();
        for (Card card : cardSet) {
            uniqueRanks.add(card.getRank());
        }
        return uniqueRanks;
    }

    private Set<Suit> getUniqueSuit() {
        Set<Suit> uniqueSuit = new HashSet<>();
        for (Card card : cardSet) {
            uniqueSuit.add(card.getSuit());
        }
        return uniqueSuit;
    }

    private boolean isSequence() {
        List<Card> cardList = new ArrayList<>(cardSet);
        List<Integer> rankWeightList = new ArrayList<>();
        for (Card card : cardList) {
            rankWeightList.add(card.getRank().getWeight());
        }
        Collections.sort(rankWeightList);
        for (int i = 0; i < rankWeightList.size() - 1; i++) {
            if (rankWeightList.get(i + 1) - rankWeightList.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    private Map<Rank, List<Card>> groupCardsByRank() {
        Map<Rank, List<Card>> groupCards = new HashMap<>();
        for (Card card : cardSet) {
            List<Card> cards = groupCards.get(card.getRank());
            if (cards == null) {
                List<Card> cardList = new ArrayList<>();
                cardList.add(card);
                groupCards.put(card.getRank(), cardList);
            } else {
                cards.add(card);
            }
        }
        return groupCards;
    }

    private Value estimateHandValue() {
        Set<Suit> suits = getUniqueSuit();
        Set<Rank> ranks = getUniqueRanks();
        switch (ranks.size()) {
            case 5:
                if (suits.size() == 1) {
                    return checkKindOfFlush();
                }
                if (isSequence()) {
                    return Value.STRAIGHT;
                }
                return Value.HIGH_CARD;
            case 4:
                return Value.ONE_PAIR;
            case 3:
                return checkTwoPairsOrThreeOfAKind();
            case 2:
                return checkFullHouseOrFourOfAKind();
            default:
                return Value.HIGH_CARD;
        }
    }

    private Value checkFullHouseOrFourOfAKind() {
        Set<Rank> ranks = getUniqueRanks();
        for (Rank r : ranks) {
            if ((groupCardsByRank().get(r).size() == 1) || (groupCardsByRank().get(r).size() == 4)) {
                return Value.FOUR_OF_A_KIND;
            }
            if ((groupCardsByRank().get(r).size() == 2) || (groupCardsByRank().get(r).size() == 3)) {
                return Value.FULL_HOUSE;
            }
        }
        return Value.HIGH_CARD;
    }

    private Value checkTwoPairsOrThreeOfAKind() {
        Set<Rank> ranks = getUniqueRanks();
        for (Rank r : ranks) {
            if (groupCardsByRank().get(r).size() == 3) {
                return Value.THREE_OF_A_KIND;
            }
            if (groupCardsByRank().get(r).size() == 2) {
                return Value.TWO_PAIR;
            }
        }
        return Value.HIGH_CARD;
    }

    private Value checkKindOfFlush() {
        if (isSequence()) {
            if (getUniqueRanks().contains(Rank.ACE)) {
                return Value.ROYAL_FLUSH;
            } else {
                return Value.STRAIGHT_FLUSH;
            }
        }
        return Value.FLUSH;
    }

    @Override
    public String toString() {
        return "<hand " +
                cardSet + ", '" +
                estimateHandValue() + "'>\n";
    }


    @Override
    public int compareTo(Hand h) {
        return h.estimateHandValue().ordinal() - this.estimateHandValue().ordinal();
    }

    public static void main(String[] args) {
        List<Hand> handList = new ArrayList<>();
        handList.add(new Hand(fromString("2D 5H KD 6C 8D"))); //
        handList.add(new Hand(fromString("5D 5H 6D 6C 8D"))); // 'Two Pairs'
        handList.add(new Hand(fromString("7S TC TH TS TD"))); // 7S TC TH TS TD 'Four of a Kind'
        handList.add(new Hand(fromString("TS JS QS KS AS"))); // TS JS QS KS AS 'Royal Flush'
        handList.add(new Hand(fromString("4D 5D 6D 7H 8D"))); // 4D 5D 6D 7H 8D 'Straight'
        handList.add(new Hand(fromString("5H 5C QD QC QS"))); // 5H 5C QD QC QS 'Full House'
        handList.add(new Hand(fromString("2D 3D 7D QD AD"))); // 2D 3D 7D QD AD 'Flush'
        handList.add(new Hand(fromString("5S 6S 7S 8S 9S"))); // 5S 6S 7S 8S 9S 'Straight Flush'
        handList.add(new Hand(fromString("4D 4H 6D 4C 8D"))); // 'Three of Kind'


        System.out.println(handList);
        Collections.sort(handList);
        System.out.println("After sort");
        System.out.println(handList);
    }
}














