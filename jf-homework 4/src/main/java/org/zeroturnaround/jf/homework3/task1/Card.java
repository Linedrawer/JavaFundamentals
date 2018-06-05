package org.zeroturnaround.jf.homework3.task1;

public final class Card {

  public final Suit suit;
  public final Rank rank;

  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return getRank()+ " of " + getSuit();
    }
}
