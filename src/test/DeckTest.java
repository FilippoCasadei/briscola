package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Deck;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
 private Deck deck;

 @BeforeEach
 void setUp() {
     deck = Deck.createDeck();
 }

 @Test
 public void testDeckCreation() {
     assertEquals(40, deck.size(), "Deck should contain 40 cards.");
 }

 @Test
 public void testDrawCardReducesSize() {
     int initialSize = deck.size();
     deck.draw();
     assertEquals(initialSize - 1, deck.size(), "Drawing a card should reduce deck size by 1.");
 }
}
