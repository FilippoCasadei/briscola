package model.state;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.GameContext;
import model.Player;

/**
 * Ending state of the game, determining and announcing the winner(s) of the game.
 */
public class WinnerState implements GameState {

    @Override
    public void handle(GameContext context) {
        List<Player> players = context.getPlayers();

        // Announce the end of the game
        System.out.println("The game has ended!");
        System.out.println("And the winner is...");

        try {
            simulateDramaticPause();

            List<Player> topPlayers = getTopScorers(players);
            String winnerMessage = generateWinnerMessage(topPlayers);
            System.out.println(winnerMessage);

            displayLeaderboard(players);

            // Transition to GameOverState
            context.nextState(new GameOverState());
        } catch (InterruptedException e) {
            System.err.println("An error occurred during the dramatic pause: " + e.getMessage());
        }
    }

    /**
     * Simulates a dramatic pause with dots to build anticipation.
     */
    private void simulateDramaticPause() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.print(".");
        }
        System.out.println();
    }

    /**
     * Displays the leaderboard in descending order of scores.
     *
     * @param players the list of players
     */
    private void displayLeaderboard(List<Player> players) {
        System.out.println("\nLeaderboard:");
        players.stream()
               .sorted(Comparator.comparingInt(Player::getScore).reversed())
               .forEach(player -> System.out.println(player + ": " + player.getScore() + " points"));
    }

    /**
     * Retrieves the top scorers (players with the highest score).
     *
     * @param players the list of players in the game
     * @return a list of players with the highest score
     */
    private List<Player> getTopScorers(List<Player> players) {
        int maxScore = players.stream()
                			  .mapToInt(Player::getScore)
                			  .max()
                			  .orElseThrow(() -> new IllegalStateException("No players found"));

        return players.stream()
                	  .filter(player -> player.getScore() == maxScore)
                	  .collect(Collectors.toList());
    }

    /**
     * Generates a message announcing the winner(s) or a tie.
     *
     * @param topScorers the list of players with the highest score
     * @return a string announcing the winner(s) or tie
     */
    private String generateWinnerMessage(List<Player> topScorers) {
    	// 1 Winner
        if (topScorers.size() == 1) {
            Player winner = topScorers.get(0);
            return "Winner: " + winner + " with " + winner.getScore() + " points! Congratulations!";
        } 
        // 2+ Tie
        else {
            int maxScore = topScorers.get(0).getScore(); 
            return "It's a tie between the following players: " +
                   topScorers.stream()
                             .map(Player::toString)
                             .collect(Collectors.joining(", ")) +
                   " with " + maxScore + " points each!";
        }
    }
}

