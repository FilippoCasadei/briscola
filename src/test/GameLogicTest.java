package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private Map<Player, Card> cardsPlayed;
    private List<Player> players;
    private Card highSuit;

    @BeforeEach
    void setUp() {
        // Setup players
        players = new ArrayList<>();
        players.add(new User("Player 1"));
        players.add(new User("Player 2"));

        // Setup played cards
        cardsPlayed = new LinkedHashMap<>();

        // Trump suit
        highSuit = new Card(Suit.SWORDS, Rank.TWO);
    }

    @Test
    void testCompareCards() {
        cardsPlayed.put(players.get(0), new Card(Suit.BATONS, Rank.KING));
        cardsPlayed.put(players.get(1), new Card(Suit.BATONS, Rank.ACE));
        Player winner = GameLogic.compareCards(cardsPlayed, highSuit, players.get(0));
        assertEquals(players.get(1), winner, "Player 2 should win because they have the highest card of the same suit.");
    }

    @Test
    void testCompareCardsWithThreePlayers() {
        players.add(new User("Player 3"));
        cardsPlayed.put(players.get(0), new Card(Suit.BATONS, Rank.ACE));
        cardsPlayed.put(players.get(1), new Card(Suit.COINS, Rank.ACE));
        cardsPlayed.put(players.get(2), new Card(Suit.SWORDS, Rank.KING));
        Player winner = GameLogic.compareCards(cardsPlayed, highSuit, players.get(0));
        assertEquals(players.get(2), winner, "Player 2 should win because they have the only trump card.");
    }

    @Test
    void testCompareCardsWithFourPlayers() {
        players.add(new User("Player 3"));
        players.add(new User("Player 4"));
        cardsPlayed.put(players.get(0), new Card(Suit.BATONS, Rank.ACE));
        cardsPlayed.put(players.get(1), new Card(Suit.SWORDS, Rank.KING));
        cardsPlayed.put(players.get(2), new Card(Suit.COINS, Rank.ACE));
        cardsPlayed.put(players.get(3), new Card(Suit.SWORDS, Rank.ACE));
        Player winner = GameLogic.compareCards(cardsPlayed, highSuit, players.get(0));
        assertEquals(players.get(3), winner, "Player 4 should win because they have the highest trump card.");
    }

    @Test
    void testCalculatePoints() {
        cardsPlayed.put(players.get(0), new Card(Suit.BATONS, Rank.THREE));
        cardsPlayed.put(players.get(1), new Card(Suit.BATONS, Rank.KING));
        cardsPlayed.put(new User("Player 3"), new Card(Suit.BATONS, Rank.ACE));
        cardsPlayed.put(new User("Player 4"), new Card(Suit.BATONS, Rank.FIVE));
        int points = GameLogic.calculatePoints(cardsPlayed);
        assertEquals(25, points, "The total points should be 25 (10 + 4 + 11 + 0).");
    }

    @Test
    void testCalculatePointsEmptyMap() {
        cardsPlayed.clear(); // Empty map
        int points = GameLogic.calculatePoints(cardsPlayed);
        assertEquals(0, points, "The total points should be 0 for an empty map.");
    }
}

