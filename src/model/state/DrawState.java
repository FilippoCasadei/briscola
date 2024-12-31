package model.state;

import java.util.List;

import model.*;

/*
 * Every player draw a card (if there are left in the deck)
 */
public class DrawState implements GameState {

    @Override
    public void handle(GameContext context) {
        System.out.println("Drawing cards...");

        Deck deck = context.getDeck();
        List<Player> players = context.getPlayers();
        Card highSuit = context.getTrumpCard(); 
        Player startingPlayer = context.getStartingPlayer(); // Player who starts drawing

        if (deck.size() >= players.size()) {
            // Normal draw
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get((players.indexOf(startingPlayer) + i) % players.size());
                currentPlayer.addCardToHand(deck.draw());
            }
            System.out.println("...Card drawing complete.");
            context.nextState(new PlayerTurnState());
        } 
        else if (deck.size() == players.size() - 1) {
            // Last draw, the last player takes the trump card
            for (int i = 0; i < players.size() - 1; i++) {
                Player currentPlayer = players.get((players.indexOf(startingPlayer) + i) % players.size());
                currentPlayer.addCardToHand(deck.draw());
            }
            // The last player takes the trump card
            Player lastPlayer = players.get((players.indexOf(startingPlayer) + players.size() - 1) % players.size());
            lastPlayer.addCardToHand(highSuit);
            System.out.println("...Final draw complete. The trump card has been assigned.");
            context.nextState(new PlayerTurnState());
        } 
        else {
            // No cards remaining in the deck
            System.out.println("No cards left to draw.");
            boolean allHandsEmpty = players.stream().allMatch(player -> player.getHand().getCards().isEmpty());

            // Transition to WinnerState if the match is finished, to next turn otherwise
            if (allHandsEmpty) {
                System.out.println("Transitioning to Winner State...");
                context.nextState(new WinnerState());
            } else {
                System.out.println("Transitioning to Player Turn State...");
                context.nextState(new PlayerTurnState());
            }
        }
    }
}

