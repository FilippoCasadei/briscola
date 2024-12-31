package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
	private Card card;

	@BeforeEach
	void setUp() {
		card = new Card(Suit.SWORDS, Rank.ACE);
	}

	@Test
	public void testCardCreation() {
		assertEquals(Suit.SWORDS, card.getSuit(), "Suit should match the initialized value.");
		assertEquals(Rank.ACE, card.getRank(), "Rank should match the initialized value.");
	}	

	@Test
	public void testCardToString() {
		assertEquals("Ace of Swords", card.toString(), "String representation should match the format.");
	}
}