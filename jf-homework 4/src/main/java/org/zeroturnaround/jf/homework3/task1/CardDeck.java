package org.zeroturnaround.jf.homework3.task1;

import java.util.*;

public class CardDeck {
    private ArrayList<Card> deck;
  /**
   * Creates a new shuffled card deck with 52 cards
   */
    public CardDeck() {
        deck = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            Rank rank = Rank.values()[i];

            for (int j = 0; j < 4; j++) {
              Card card = new Card(Suit.values()[j], rank);
              deck.add(card);
            }
        }
        shuffle();
    }

  /**
   * Shuffles the remaining cards in the deck.
   */
    public void shuffle() {
      Collections.shuffle(deck);
  }

  /**
   * Returns the topmost card in the pile or NULL if none remaining
   */
    public Card take() {
        if (deck.isEmpty()) return null;
        int lastCard = deck.size() -1;
        Card topCard = deck.get(lastCard);
        deck.remove(lastCard);
        return topCard;
    }

  /**
   * Adds the card to the bottom of the pile.
   */
    public void add(Card... card) {
        for (Card c: card) {
            deck.add(0, c);
            removeDuplicates();
        }
        removeDuplicates();
    }

  /**
   * Returns the number of cards remaining in the deck (0...52)
   */
    public int size() {
        return deck.size();
    }

    public void showCards() {
        System.out.println("\n\n Showing Cards !!!");
        for(Card e :deck) {
            System.out.println(e.getRank()+ " of " + e.getSuit());
        }
    }
    /**
     * Checks deck for duplicates
     */
    public void removeDuplicates(){
        for (int i = 0; i < deck.size(); i++) {
            Card a = deck.get(i);
            for (int j = i+1; j < deck.size() - i ; j++) {
                Card b = deck.get(j);
                if ((a.getRank().equals(b.getRank()))&&(a.getSuit().equals(b.getSuit()))){

                        System.err.println("The duplicate was found, removing it...");
                        deck.remove(a);

                }
            }
        }

    }
}
