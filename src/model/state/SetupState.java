package model.state;

import java.util.List;

import model.Deck;
import model.GameContext;
import model.Player;

public class SetupState implements GameState {

    private static final int N_OF_CARDS_IN_HAND = 3;

    @Override
    public void handle(GameContext context) {
        // Log game preparation
        System.out.println("Game setup: Distributing cards...");

        List<Player> players = context.getPlayers();
        Deck deck = context.getDeck();

        // Special case: Remove one card if there are 3 players
        if (players.size() == 3) {
            deck.draw();
            System.out.println("One card is removed from the deck for a 3-player game.");
        }

        // Distribute 3 cards to each player
        for (Player player : players) {
            for (int i = 0; i < N_OF_CARDS_IN_HAND; i++) {
                player.addCardToHand(deck.draw());
            }
        }

        // Select the trump card
        context.setTrumpCard(deck.draw());
        System.out.println("The trump card is: " + context.getTrumpCard());

        // Transition to Player Turn State
        System.out.println("Setup completed! Transitioning to Player Turn State...");
        context.nextState(new PlayerTurnState());
    }
}
