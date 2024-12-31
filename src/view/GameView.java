package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameView extends JFrame {

    public GameView() {
        // Configurazione base della finestra
        setTitle("Briscola - Partita");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrare la finestra
        setLayout(new BorderLayout());

        // Pannello principale con immagine di sfondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica e disegna l'immagine di sfondo
                ImageIcon tableBackground = new ImageIcon("table.jpg"); // Percorso dell'immagine del tavolo
                g.drawImage(tableBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null); // Posizionamento assoluto per maggiore controllo
        add(mainPanel, BorderLayout.CENTER);

        // Mazzo e Briscola (parte centrale sinistra)
        JLabel deckLabel = new JLabel(new ImageIcon("deck.png")); // Mazzo
        JLabel trumpLabel = new JLabel(new ImageIcon("trump.png")); // Briscola
        deckLabel.setBounds(50, 200, 80, 120); // Posizione e dimensioni del mazzo
        trumpLabel.setBounds(150, 200, 80, 120); // Posizione e dimensioni della briscola
        mainPanel.add(deckLabel);
        mainPanel.add(trumpLabel);

        // Carte del giocatore (parte centrale bassa)
        JPanel playerCardsPanel = new JPanel();
        playerCardsPanel.setOpaque(false); // Sfondo trasparente
        playerCardsPanel.setBounds(250, 400, 300, 150); // Posizione e dimensioni del pannello
        playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Allineamento orizzontale

        // Aggiungi 3 carte del giocatore
        for (int i = 0; i < 3; i++) {
            JLabel card = new JLabel(new ImageIcon("player_card" + (i + 1) + ".png"));
            playerCardsPanel.add(card);
        }
        mainPanel.add(playerCardsPanel);

        // Carte dell'avversario (parte centrale alta)
        JPanel opponentCardsPanel = new JPanel();
        opponentCardsPanel.setOpaque(false); // Sfondo trasparente
        opponentCardsPanel.setBounds(250, 50, 300, 150); // Posizione e dimensioni del pannello
        opponentCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Allineamento orizzontale

        // Aggiungi 3 carte coperte dell'avversario
        for (int i = 0; i < 3; i++) {
            JLabel card = new JLabel(new ImageIcon("back_card.png"));
            opponentCardsPanel.add(card);
        }
        mainPanel.add(opponentCardsPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView();
            gameView.setVisible(true);
        });
    }
}