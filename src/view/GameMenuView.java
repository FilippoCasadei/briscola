package view;

import javax.swing.*;
import java.awt.*;

public class GameMenuView extends JFrame {
    public GameMenuView() {
        // Configurazione base della finestra
        setTitle("Menu del Gioco - Briscola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrare la finestra
        setLayout(new BorderLayout());

        // Pannello per il titolo
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel("Briscola - Menu del Gioco");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Pannello principale con immagine di sfondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica e disegna l'immagine di sfondo
                ImageIcon backgroundImage = new ImageIcon("images/table.jpg"); // Percorso dell'immagine
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // Per centrare i componenti
        add(mainPanel, BorderLayout.CENTER);

        // Pannello con le opzioni
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false); // Sfondo trasparente
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // Scelta della modalità di gioco
        JLabel modeLabel = new JLabel("Modalità di Gioco:");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<String> modeComboBox = new JComboBox<>(new String[]{"2 Giocatori", "3 Giocatori", "4 Giocatori"});

        // Scelta della difficoltà della CPU
        JLabel difficultyLabel = new JLabel("Difficoltà della CPU:");
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});

        // Bottone per iniziare il gioco
        JButton startButton = new JButton("Inizia Gioco");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Aggiungi i componenti al pannello delle opzioni
        optionsPanel.add(modeLabel);
        optionsPanel.add(modeComboBox);
        optionsPanel.add(difficultyLabel);
        optionsPanel.add(difficultyComboBox);

        // Aggiungi il pannello delle opzioni e il bottone al pannello principale
        mainPanel.add(optionsPanel);
        add(startButton, BorderLayout.SOUTH);

        // Azione per il bottone
        startButton.addActionListener(e -> {
            String selectedMode = (String) modeComboBox.getSelectedItem();
            String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
            JOptionPane.showMessageDialog(this,
                    "Modalità selezionata: " + selectedMode + "\nDifficoltà CPU: " + selectedDifficulty,
                    "Informazioni Gioco", JOptionPane.INFORMATION_MESSAGE);
            // Puoi aggiungere qui il codice per avviare il gioco con le opzioni selezionate
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameMenuView menuView = new GameMenuView();
            menuView.setVisible(true);
        });
    }
}
