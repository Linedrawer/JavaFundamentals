package org.zeroturnaround.jf.homework3;

import org.junit.Assert;
import org.junit.Test;
import org.zeroturnaround.jf.homework3.task1.Card;
import org.zeroturnaround.jf.homework3.task1.CardDeck;
import org.zeroturnaround.jf.homework3.task1.Rank;
import org.zeroturnaround.jf.homework3.task1.Suit;

public class CardDeckTest {

  @Test
  public void testCardDeckCreates52Cards() {
    Assert.assertEquals(52, new CardDeck().size());
  }

  @Test
  public void testCardDeckContainsNoDuplicateCards() {
    // TODO: implement me
    CardDeck cardDeck = new CardDeck();
    int i = cardDeck.size();
    Card[] cards = {new Card(Suit.CLUBS, Rank.ACE)};
    cardDeck.add(cards);
    int j = cardDeck.size();
    Assert.assertEquals(i, j);
  }

  @Test
  public void emptyDeckTest(){
    CardDeck cardDeck = new CardDeck();
    for (int i = 0; i < 52; i++) {
      cardDeck.take();
    }
    Card actual = cardDeck.take();
    Card expected = null;
    Assert.assertEquals(expected, actual);
  }



}
