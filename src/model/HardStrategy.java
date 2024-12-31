package model;

import java.util.Map;

import java.util.*;

public class HardStrategy implements CpuStrategy {

	/*
	 * SPIEGARE FUNZIONAMENTO
	 */
	@Override
	public Card chooseCard(GameContext context, Cpu cpu) {
		Map<Player, Card> cardsPlayedThisTurn = context.getCardsPlayedThisTurn();
		List<Player> players = context.getPlayers();
		Player startingPlayer = context.getStartingPlayer();
	    Card trumpCard = context.getTrumpCard();
	    int pointsOnTable = cardsPlayedThisTurn.values().stream()
	    											 	.mapToInt(card -> card.getRank().getCardPoints())
	    											 	.sum();
	    int nCardsPlayedThisTurn = cardsPlayedThisTurn.size();
	    
	    // Strategies as starting player
	    if (cpu.equals(startingPlayer)) {
	        return playFirstTurn(cpu, trumpCard);
	    }

	    // Current opponent to win the turn
	    Player currentWinnerTurn = GameLogic.determineTurnWinner(cardsPlayedThisTurn, trumpCard, startingPlayer);
	    Card winningCard = cardsPlayedThisTurn.get(currentWinnerTurn);
	    
	    // High-risk strategy to prevent current leading opponent to win
	    if (mustPreventWin(players, currentWinnerTurn, pointsOnTable)) {
	        return playLastTurn(winningCard, trumpCard, cpu);
	    }

	    // High-reward strategy to secure win
	    if (canWinGame(players, cpu, pointsOnTable)) {
	        return playLastTurn(winningCard, trumpCard, cpu);
	    }

	    // Strategies based on turn position
	    if (nCardsPlayedThisTurn == (players.size()-1)) {
	        return playLastTurn(winningCard, trumpCard, cpu);
	    } else {
	        return playMiddleTurn(winningCard, trumpCard, cpu);
	    }
	}
	
	
	/**
	 * Strategy when CPU is the first to play in the turn.
	 * Plays the weakest non-trump card or the weakest trump card if no other option is available.
	 */
	private Card playFirstTurn(Cpu cpu, Card trumpCard) {
	    Card weakestNonTrump = getWeakestCardNonTrump(cpu, trumpCard.getSuit());
	    return weakestNonTrump != null ? weakestNonTrump :
	    								 getWeakestCardOfSuit(cpu, trumpCard.getSuit());
	}
	
	/**
	 * Strategy when CPU plays in the middle of the turn.
	 * Tries to win with the weakest winning card or plays a weak card otherwise.
	 */
	private Card playMiddleTurn(Card winningCard, Card trumpCard, Cpu cpu) {
	    return cpu.getHand().getCards().stream()
	              					   .filter(card -> GameLogic.compareCards(winningCard, card, trumpCard) < 0) // Find cards that can win
	              					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))    // Pick the least expensive
	              					   .orElse(getWeakestCard(cpu));                                           // Otherwise, play weakest card
	}
	
	/**
	 * Strategy when CPU is the last to play in the turn.
	 * Prioritizes winning with the strongest winning card or plays the weakest card.
	 */
	private Card playLastTurn(Card winningCard, Card trumpCard, Cpu cpu) {
	    return cpu.getHand().getCards().stream()
	              					   .filter(card -> GameLogic.compareCards(winningCard, card, trumpCard) < 0) // Find cards that can win
	              					   .max(Comparator.comparingInt(card -> card.getRank().getCardPoints()))    // Use the strongest among winners
	              					   .orElse(getWeakestCard(cpu));                                           // Otherwise, play weakest card
	}
	
	/**
	 * Determines if the CPU needs to make a high-risk play to prevent an opponent player from winning.
	 */
	private boolean mustPreventWin(List<Player> players, Player opponent, int pointsOnTable) {
	    int opponentPoints = opponent.getScore();
		int winningThreshold = GameLogic.calculateWinningThreshold(players, opponent);
		
		return opponentPoints + pointsOnTable >= winningThreshold;
	}
	
	/**
	 * Determines if the CPU can win the game with the current hand.
	 */
	private boolean canWinGame(List<Player> players, Player cpu, int pointsOnTable) {
		int cpuPoints = cpu.getScore();
		int winningThreshold = GameLogic.calculateWinningThreshold(players, cpu);
		
		return cpuPoints + pointsOnTable >= winningThreshold;
	}
	
	/**
	 * Returns the weakest card (lowest points) of a specific suit.
	 * If no cards match, returns null.
	 */
	private Card getWeakestCardOfSuit(Cpu cpu, Suit suit) {
	    return cpu.getHand().getCards().stream()
	              					   .filter(card -> card.getSuit() == suit)
	              					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
	              					   .orElse(null);
	}
	
	/**
	 * Returns the weakest card (lowest points) non trump.
	 * If no cards match, returns null.
	 */
	private Card getWeakestCardNonTrump(Cpu cpu, Suit trump) {
	    return cpu.getHand().getCards().stream()
	              					   .filter(card -> card.getSuit() != trump)
	              					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
	              					   .orElse(null);
	}

	/**
	 * Returns the strongest card (highest points) of a specific suit.
	 * If no cards match, returns null.
	 */
	private Card getStrongestCardOfSuit(Cpu cpu, Suit suit) {
	    return cpu.getHand().getCards().stream()
	              .filter(card -> card.getSuit() == suit)
	              .max(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
	              .orElse(null);
	}

	/**
	 * Returns the weakest card in the hand, regardless of suit.
	 */
	private Card getWeakestCard(Cpu cpu) {
	    return cpu.getHand().getCards().stream()
	              					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
	              					   .orElseThrow(() -> new IllegalStateException("Hand is empty"));
	}

	/**
	 * Returns the strongest card in the hand, regardless of suit.
	 */
	private Card getStrongestCard(Cpu cpu) {
	    return cpu.getHand().getCards().stream()
	              					   .max(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
	              					   .orElseThrow(() -> new IllegalStateException("Hand is empty"));
	}

	/**
	 * Checks if the CPU can win the turn with any card.
	 */
	private boolean canWinTurn(Card winningCard, Card trumpCard, Cpu cpu) {
	    return cpu.getHand().getCards().stream()
	              					   .anyMatch(card -> GameLogic.compareCards(winningCard, card, trumpCard) < 0);
	}

	
	
	
    /**
     * Chooses the best card for the CPU to play based on the current game context.
     * 
     * @param context The current game context, including cards played and the trump card.
     * @param cpu     The CPU player making the decision.
     * @return The card chosen by the CPU to play.
     */
//    @Override
//    public Card chooseCard(GameContext context, Cpu cpu) {
//        Map<Player, Card> cardsPlayedThisTurn = context.getCardsPlayedThisTurn();
//        Card trumpCard = context.getTrumpCard();
//        int myPoints = cpu.getScore();
//
//        // Determine the current winning card in this turn
//        Player startingPlayer = context.getStartingPlayer();
//        Player currentLeader = GameLogic.determineTurnWinner(cardsPlayedThisTurn, trumpCard, startingPlayer);
//        Card winningCard = cardsPlayedThisTurn.get(currentLeader);
//
//        // Check if the CPU can win the turn
//        if (canWinTurn(winningCard, trumpCard, cpu)) {
//            int pointsOnTable = GameLogic.calculatePointsOnTable(cardsPlayedThisTurn);
//
//            // Play the minimum card to win if strategic
//            if (pointsOnTable > 4 || myPoints + pointsOnTable >= 61) {
//                return chooseMinimumWinningCard(winningCard, trumpCard, cpu);
//            }
//        }
//
//        // Attempt to hinder the leader if present
//        if (!currentLeader.equals(cpu)) {
//            return chooseCardToHinder(cardsPlayedThisTurn, currentLeader, trumpCard, cpu);
//        }
//
//        // Handle remaining important trump cards
//        if (areImportantTrumpCardsRemaining(context, cpu)) {
//            return chooseCardToHandleTrumps(trumpCard, cpu);
//        }
//
//        // Play conservatively by losing intentionally
//        return chooseCardToLose(cpu);
//    }
//
//    private boolean canWinTurn(Card winningCard, Card trumpCard, Cpu cpu) {
//        return cpu.getHand().getCards().stream()
//                  					   .anyMatch(card -> GameLogic.compareCards(winningCard, card, trumpCard) < 0);
//    }
//
//    private Card chooseMinimumWinningCard(Card winningCard, Card trumpCard, Cpu cpu) {
//        return cpu.getHand().getCards().stream()
//                  					   .filter(card -> GameLogic.compareCards(winningCard, card, trumpCard) < 0)
//                  					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
//                  					   .orElse(chooseCardToLose(cpu));
//    }
//
//    private Card chooseCardToHinder(Map<Player, Card> cardsPlayedThisTurn, Player leader, Card trumpCard, Cpu cpu) {
//        Card leaderCard = cardsPlayedThisTurn.get(leader);
//        return cpu.getHand().getCards().stream()
//                  					   .filter(card -> GameLogic.compareCards(leaderCard, card, trumpCard) < 0)
//                  					   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
//                  					   .orElse(chooseCardToLose(cpu));
//    }
//
//    private Card chooseCardToHandleTrumps(Card trumpCard, Cpu cpu) {
//        return cpu.getHand().getCards().stream()
//                  					   .filter(card -> card.getSuit() == trumpCard.getSuit())
//                  					   .max(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
//                  					   .orElse(chooseCardToLose(cpu));
//    }
//
//    private boolean areImportantTrumpCardsRemaining(GameContext context, Cpu cpu) {
//        Suit trumpSuit = context.getTrumpCard().getSuit();
//        return context.getCardPlayedThisMatch().stream()
//                      						   .filter(card -> card.getSuit() == trumpSuit)
//                      						   .count() < 10; // Example threshold
//    }
//
//    private Card chooseCardToLose(Cpu cpu) {
//        return cpu.getHand().getCards().stream()
//        							   .min(Comparator.comparingInt(card -> card.getRank().getCardPoints()))
//        							   .orElseThrow(() -> new IllegalStateException("No cards in hand."));
//    }
}

