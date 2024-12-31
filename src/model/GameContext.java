package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.state.GameState;

public class GameContext {

    private GameState currentState;  	//  Current state of the game
    private List<Player> players; 		// List of players participating in the game. This value can range from 2 to 4 players.
    private Map<Player, Card> cardsPlayedThisTurn; 	// Maps each player to the card they have played in the current turn.
    private Player startingPlayer; 	// The player who starts the current turn
    private Deck deck;
    // TODO: NON E UTILIZZATO
//    private List<Card> cardPlayedThisMatch;  // Cards played during the entire match
    private Card trumpCard;
    private boolean gameOver; 	// Indicates whether the game is over

    /**
     * Constructs a GameContext with an initial state and a list of players.
     *
     * @param initialState the initial state of the game
     * @param players the list of players participating in the game
     */
    public GameContext(GameState initialState, List<Player> players) {
        this.currentState = initialState;
        this.players = players;
        this.cardsPlayedThisTurn = new HashMap<>();
        this.startingPlayer = players.get(0);  // Initially, the first player starts
        this.deck = Deck.createDeck();		   // Creates and shuffles a deck
//        this.cardPlayedThisMatch = new ArrayList<>();
        this.trumpCard = null;
        this.gameOver = false;
    }
    
    /**
     * Executes the game flow by handling states until the game is over.
     */
    public void play() {
        while (!gameOver) {
            currentState.handle(this);
        }
    }

    /**
     * Executes a single game state.
     */
    public void executeState() {
        currentState.handle(this);
    }

    /**
     * Transitions to the next game state.
     *
     * @param state the next state of the game
     */
    public void nextState(GameState state) {
    	// Log to separate states
    	System.out.println("***********************************************");
        this.currentState = state;
    }

    /**
     * @return the current state of the game
     */
    public GameState getCurrentState() {
        return currentState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<Player, Card> getCardsPlayedThisTurn() {
        return cardsPlayedThisTurn;
    }

    /**
     * Records a card played by a player.
     *
     * @param player the player who played the card
     * @param card the card played
     */
    public void addCardPlayedThisTurn(Player player, Card card) {
        cardsPlayedThisTurn.put(player, card);
    }

    /**
     * Clears the map of cards played in the current turn.
     */
    public void resetCardsPlayed() {
        cardsPlayedThisTurn.clear();
    }
    
    public void increaseScoreToPlayer(Player player, int points) {
    	player.increaseScore(points);
    }

    public Player getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public Deck getDeck() {
        return this.deck;
    }

    
//    public void addCardPlayedThisMatch(Card cardPlayed) {
//    	this.cardPlayedThisMatch.add(cardPlayed);
//    }

    public Card getTrumpCard() {
        return this.trumpCard;
    }

    public void setTrumpCard(Card trumpCard) {
        this.trumpCard = trumpCard;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}  
  
