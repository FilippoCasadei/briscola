package model;

public abstract class Player {
    private String name;
    private Hand hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.score = 0;
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    /**
     * Gioca una carta: sceglie una carta e la rimuove dalla mano.
     */
    public Card playCard(GameContext context) {
    	Card card = chooseCard(context);
        hand.removeCard(card);
        return card;
    }
    
    /**
     * Metodo astratto per scegliere una carta.
     * Sarà implementato nelle sottoclassi (es. `User` o `Cpu`).
     */
    public abstract Card chooseCard(GameContext context);
    
    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public Hand getHand() {
        return hand;
    }
    
    public String toString() {
    	return this.name;
    }
}

