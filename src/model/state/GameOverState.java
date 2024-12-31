package model.state;

import model.GameContext;

/**
 * Represents the final state of the game. 
 * This state is reached when the game ends, and it provides a clean conclusion.
 */
public class GameOverState implements GameState {

    @Override
    public void handle(GameContext context) {
    	// Log the end of the match
        System.out.println("\n=== GAME OVER ===");
        System.out.println("Thank you for playing!");
        
        // Mark the game as finished
        context.setGameOver(true);
    }
}

