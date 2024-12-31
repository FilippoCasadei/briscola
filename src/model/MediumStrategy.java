package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MediumStrategy implements CpuStrategy {

	/*
	 * SPIEGARE FUNZIONAMENTO
	 */
    @Override
    public Card chooseCard(GameContext context, Cpu cpuPlayer) {
    	Hand hand = cpuPlayer.getHand();
        Map<Player, Card> cardsPlayed = context.getCardsPlayedThisTurn();
        Card trumpCard = context.getTrumpCard();
        boolean isFirstPlayer = context.getStartingPlayer().equals(cpuPlayer);

        // If the CPU is the first player, it plays the lowest card that is not a trump.
        // Otherwise, it tries to win.
        return isFirstPlayer ? playLowestNonTrump(hand, trumpCard) :
        					   playToWin(hand, cardsPlayed, trumpCard);
    }

    private Card playLowestNonTrump(Hand hand, Card highSuit) {
        List<Card> cards = hand.getCards();
        Card lowestNonTrump = null;

        // Find the lowest non-trump card.
        for (Card card : cards) {
            if (!card.getSuit().equals(highSuit.getSuit()) &&
                (lowestNonTrump == null || card.getRank().ordinal() < lowestNonTrump.getRank().ordinal())) {
                lowestNonTrump = card;
            }
        }

        // If no non-trump cards exist, play the lowest card.
        return lowestNonTrump != null ? lowestNonTrump : playLowestCard(hand);
    }

    private Card playToWin(Hand hand, Map<Player, Card> cardsPlayed, Card highSuit) {
        Card leadingCard = new ArrayList<>(cardsPlayed.values()).get(0); // First card played in the round.
        Card highestWinningCard = null;

        // Find the highest winning card of the same suit as the leading card.
        for (Card card : hand.getCards()) {
            if (card.getSuit().equals(leadingCard.getSuit()) &&
                card.getRank().ordinal() > leadingCard.getRank().ordinal() &&
                (highestWinningCard == null || card.getRank().ordinal() > highestWinningCard.getRank().ordinal())) {
                highestWinningCard = card;
            }
        }

        // If it cannot win with the same suit, try to win with a trump card.
        if (highestWinningCard == null) {
            for (Card card : hand.getCards()) {
                if (card.getSuit().equals(highSuit.getSuit()) &&
                    (highestWinningCard == null || card.getRank().ordinal() < highestWinningCard.getRank().ordinal())) {
                    highestWinningCard = card;
                }
            }
        }

        // If it cannot win, play the lowest card.
        return highestWinningCard != null ? highestWinningCard : playLowestCard(hand);
    }

    private Card playLowestCard(Hand hand) {
        Card lowestCard = null;
        for (Card card : hand.getCards()) {
            if (lowestCard == null || card.getRank().ordinal() < lowestCard.getRank().ordinal()) {
                lowestCard = card;
            }
        }
        return lowestCard;
    }

}

