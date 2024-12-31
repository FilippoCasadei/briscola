package model.state;

import java.util.Map;

import model.*;

public class ScoringState implements GameState {

    @Override
    public void handle(GameContext context) {
        System.out.println("Calculating points...");

        // Retrieve cards played in the current turn
        Map<Player, Card> cardsPlayed = context.getCardsPlayedThisTurn();
        Card trumpCard = context.getTrumpCard();
        Player startingPlayer = context.getStartingPlayer();

        // Determine the winner of the turn
        Player winnerTurnPlayer = GameLogic.determineTurnWinner(cardsPlayed, trumpCard, startingPlayer);

        // Award points to the winner of the turn
        int points = GameLogic.calculatePointsOnTable(cardsPlayed);
        context.increaseScoreToPlayer(winnerTurnPlayer, points);

        // Update the starting player for the next turn
        context.setStartingPlayer(winnerTurnPlayer);

        // Log the results of the scoring phase
        System.out.println(points + " points awarded to " + winnerTurnPlayer);

        // Clear the cards played this turn
        context.resetCardsPlayed();

        // Transition to the Draw State
        System.out.println("Transitioning to Draw State...");
        context.nextState(new DrawState());
    }
}
