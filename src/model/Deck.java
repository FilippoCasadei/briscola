package model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deck;

    // Costruttore privato per utilizzare il Factory Pattern
    private Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    // Factory method per creare un mazzo standard di 40 carte
    public static Deck createDeck() {	
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.push(new Card(suit, rank));
            }
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }
    
    // Metodo per pescare una carta TODO: Scegliere tra Optional e non Optional
    public Card draw() {
        return deck.isEmpty() ? null : 
        						deck.pop();
    }
//    public Optional<Card> draw() {
//        return deck.isEmpty() ? Optional.empty() :
//    							  Optional.of(deck.pop());
//    }

    // Metodo per vedere quante carte restano
    public int size() {
        return deck.size();
    }

    // Metodo per verificare se il mazzo è vuoto
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    // Metodo per ottenere una rappresentazione immutabile delle carte rimaste nel deck
    public List<Card> getCards() {
        return Collections.unmodifiableList(deck);
    }
}

