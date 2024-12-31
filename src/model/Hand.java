package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player's hand of cards in a card game.
 */
public class Hand {

    private final List<Card> hand;

    /**
     * Initializes an empty hand.
     */
    public Hand() {
        this.hand = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to be added
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     *
     * @param card the card to be removed
     * @return the removed card
     * @throws IllegalArgumentException if the card is not in the hand
     */
    public Card removeCard(Card card) {
        if (hand.remove(card)) {
            return card;
        } else {
            throw new IllegalArgumentException("Card " + card + " is not in the hand!");
        }
    }

    /**
     * Checks if a specific card is present in the hand.
     *
     * @param card the card to check
     * @return true if the card is in the hand, false otherwise
     */
    public boolean hasCard(Card card) {
        return hand.contains(card);
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(hand);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Card card : this.hand) {
            sb.append(card).append(", ");
        }
        // Remove the last ','
        if (!hand.isEmpty()) {
            sb.setLength(sb.length() - 2); 
        }
        sb.append("]");
        return sb.toString();
    }
}
