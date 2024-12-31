package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {

	public User(String name) {
		super(name);
	}

	@Override
	public Card chooseCard(GameContext context) {
	    // Mostra all'utente le carte disponibili
	    System.out.println("Le tue carte: " + getHand().getCards());
	    System.out.print("Scegli una carta da giocare (0-" + (getHand().getCards().size() - 1) + "): ");
	    Scanner scanner = new Scanner(System.in);

	    int index = -1;

	    // Continua a chiedere l'input fino a quando non è valido
	    while (index < 0 || index >= getHand().getCards().size()) {
	        try {
	            // Input dell'indice della carta
	            index = scanner.nextInt();
	            
	            if (index < 0 || index >= getHand().getCards().size()) {
	                System.out.println("Indice fuori dal range. Riprova.");
	            }
	        } catch (InputMismatchException e) {
	            // Gestisce l'errore nel caso l'utente inserisca un input non numerico
	            System.out.println("Input non valido. Inserisci un numero intero.");
	            scanner.nextLine(); // Pulisce il buffer dello scanner
	        }
	    }

	    // Ritorna la carta scelta
	    return getHand().getCards().get(index);
	}

}
