package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardView {

    // Metodo per caricare una carta in base al nome del file
    public ImageView showCard(String cardName) {
        // Carica l'immagine della carta dal percorso relativo
        Image image = new Image("file:images/" + cardName + ".png"); // Nome del file es. "asso_coppe.jpg"
        
        // Crea un ImageView per visualizzare l'immagine
        ImageView imageView = new ImageView(image);
        
        // Imposta dimensioni fisse per l'immagine (puoi modificarle come vuoi)
        imageView.setFitWidth(100);  // Imposta la larghezza
        imageView.setFitHeight(150); // Imposta l'altezza
        
        return imageView;
    }
}