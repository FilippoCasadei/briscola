package model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GameLogic {
	
	private static final int TOTAL_POINTS = 120;

    /**
     * Determines the winner of the turn based on the cards played and the trump suit.
     * 
     * @param cardsPlayed     Map of players and their played cards.
     * @param trumpCard       The trump card for the game, which determines the trump suit.
     * @param startingPlayer  The player who started the turn.
     * @return The player who won the turn.
     */
    public static Player determineTurnWinner(Map<Player, Card> cardsPlayed, Card trumpCard, Player startingPlayer) {
        Player winningPlayer = startingPlayer;
        Card bestCard = cardsPlayed.get(winningPlayer);

        for (Map.Entry<Player, Card> entry : cardsPlayed.entrySet()) {
            Player currentPlayer = entry.getKey();
            Card currentCard = entry.getValue();

            if (!currentPlayer.equals(winningPlayer) && compareCards(bestCard, currentCard, trumpCard) < 0) {
                winningPlayer = currentPlayer;
                bestCard = currentCard;
            }
        }
        return winningPlayer;
    }

    /**
     * Compares two cards to determine which is stronger.
     * 
     * @param card1     The first card.
     * @param card2     The second card.
     * @param trumpCard The trump card for the game, determining the trump suit.
     * @return Negative if card2 is stronger, positive if card1 is stronger, 0 if they are equal.
     */
    public static int compareCards(Card card1, Card card2, Card trumpCard) {
        // Compare cards of the same suit
        if (card1.getSuit() == card2.getSuit()) {
            return Integer.compare(card1.getRank().ordinal(), card2.getRank().ordinal());
        }

        // Card2 is a trump card
        if (card2.getSuit() == trumpCard.getSuit()) {
            return -1;
        }

        // Card1 is a trump card
        if (card1.getSuit() == trumpCard.getSuit()) {
            return 1;
        }

        // Neither card is a trump card, and suits differ
        return 0;
    }

    /**
     * Calculates the total points of the cards played during a turn.
     * 
     * @param cardsPlayed Map of players and their played cards.
     * @return The total points of all the cards played.
     */
    public static int calculatePointsOnTable(Map<Player, Card> cardsPlayed) {
        return cardsPlayed.values().stream()
                          		   .mapToInt(card -> card.getRank().getCardPoints())
                          		   .sum();
    }
    
    /**
     * Calculates the winning threshold for one player based on the number of players and their points.
     * @param players The list of players in the game.
     * @param player The player whose winning threshold is being calculated.
     * @return The minimum points required to ensure victory.
     */
    public static int calculateWinningThreshold(List<Player> players, Player player) {
        int nPlayers = players.size();
        
        // In a 2-player game, the player needs at least 61 points to win
        if (nPlayers == 2) {
            return 61;
        }
        
        // Calculate the total points of all players in the game
        int totalPointsObtained = players.stream()
                                         .mapToInt(Player::getScore)
                                         .sum();
        
        // Find the opponent with the highest points (excluding the current player)
        Player leaderPointsOpponent = players.stream()
                                             .filter(p -> !p.equals(player))  // Exclude the current player
                                             .max(Comparator.comparingInt(Player::getScore))
                                             .orElseThrow(() -> new RuntimeException("No opponent found"));
        
        // Calculate the remaining points in the game
        int pointsRemaining = TOTAL_POINTS - totalPointsObtained;
        
        // Calculate the minimum points required for victory
        int winningThreshold = (pointsRemaining + (player.getScore() - leaderPointsOpponent.getScore())) / 2 + 1;
        
        return winningThreshold;
    }
//    public static int calculateWinningThreshold(int totalPlayers, int otherPlayersPoints) {
//        // Total points in the game are always 120.
//        int pointsRemaining = 120 - otherPlayersPoints;
//
//        // In a 2-player game, you need at least 61 points to win.
//        if (totalPlayers == 2) {
//            return 61;
//        }
//
//        // In games with more players, you need more than the average.
//        return (pointsRemaining / totalPlayers) + 1;
//    }

}

//public class GameLogic {
//	
//	public static Player compareCards(Map<Player, Card> cardsPlayed, Card highSuit, Player startingPlayer) {
//	    Player winnerTurnPlayer = startingPlayer;
//	    Card bestCard = cardsPlayed.get(winnerTurnPlayer);
//
//	    // Itera sui giocatori e confronta le carte
//	    for (Map.Entry<Player, Card> entry : cardsPlayed.entrySet()) {
//	        Player currentPlayer = entry.getKey();
//	        Card currentCard = entry.getValue();
//
//	        // Salta il confronto con il giocatore iniziale
//	        if (currentPlayer.equals(winnerTurnPlayer)) {
//	            continue;
//	        }
//
//	        // Confronta la carta attuale con la migliore finora
//	        if (compareTwoCards(bestCard, currentCard, highSuit) < 0) {
//	            winnerTurnPlayer = currentPlayer;
//	            bestCard = currentCard;
//	        }
//	    }
//
//	    return winnerTurnPlayer;
//	}
//	
//	
//	
//	
//	// sceglie la vincitrice tra 2 carte di due giocatori (primo e secondo)
//	public static Player compareTwoCards(Card card1, Card card2, Card highSuit, Player firstPlayer, Player secondPlayer) {
//	    return isSameSuit(card1, card2) ? compareByRank(card1, card2, firstPlayer, secondPlayer) :
//	    								  compareBySuit(card1, card2, highSuit, firstPlayer, secondPlayer);
//	}
//
//	public static boolean isSameSuit(Card card1, Card card2) {
//	    return card1.getSuit() == card2.getSuit();
//	}
//
//	public static Player compareBySuit(Card card1, Card card2, Card highSuit, Player firstPlayer, Player secondPlayer) {
//	    return card2.getSuit() != highSuit.getSuit() ? firstPlayer :        										   
//	    											   secondPlayer;
//	}
//
//	public static Player compareByRank(Card card1, Card card2, Player firstPlayer, Player secondPlayer) {
//	    return (card1.getRank().ordinal() > card2.getRank().ordinal()) ? firstPlayer :
//	    																 secondPlayer;
//	}
//	
//	// TODO: da modificare con maps
//	public static int calculatePoints(List<Card> cards) {
//		int points = 0;
//		
//		for (Card c : cards) {
//			points += c.getRank().getCardPoints();
//		}
//		
//		return points;
//	}
//
//}
