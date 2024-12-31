package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
 private Hand hand;
 private Card card;

 @BeforeEach
 void setUp() {
     hand = new Hand();
     card = new Card(Suit.BATONS, Rank.KING);
 }

 @Test
 public void testHandAddition() {
     hand.addCard(card);
     assertTrue(hand.getCards().contains(card), "Hand should contain the added card.");
 }

 @Test
 public void testHandRemoval() {
     hand.addCard(card);
     hand.removeCard(card);
     assertFalse(hand.getCards().contains(card), "Hand should not contain the removed card.");
 }

 @Test
 public void testHandSize() {
     hand.addCard(card);
     hand.addCard(card);
     hand.addCard(card);
     assertEquals(3, hand.getCards().size(), "Hand size should match the number of added cards.");
 }
}
