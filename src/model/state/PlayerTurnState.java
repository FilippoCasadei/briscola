package model.state;

import java.util.List;

import model.Card;
import model.GameContext;
import model.Player;

public class PlayerTurnState implements GameState {

    @Override
    public void handle(GameContext context) {

        List<Player> players = context.getPlayers();
        Player startingPlayer = context.getStartingPlayer();
        int startingPlayerIndex = players.indexOf(startingPlayer);

        // Each player takes their turn
        for (int i = 0; i < players.size(); i++) {
            int playerIndex = (startingPlayerIndex + i) % players.size();
            Player currentPlayer = players.get(playerIndex);

            // Log the start of the player's turn
            System.out.println("Player " + currentPlayer + "'s turn...");

            // The player plays a card
            Card cardPlayed = currentPlayer.playCard(context);
            context.addCardPlayedThisTurn(currentPlayer, cardPlayed);

            // Log the card played
            System.out.println("Card played: " + cardPlayed);

            // Log the end of the player's turn
            System.out.println("...End of Player " + currentPlayer + "'s turn.");
        }

        // Log the end of the round
        System.out.println("All players have played their cards!");

        // Transition to the scoring phase
        System.out.println("Transitioning to the Scoring State...");
        context.nextState(new ScoringState());
    }
}
