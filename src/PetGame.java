import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

// PetGame puts the separate UI parts together in one window.
public class PetGame {

    private Player player;
    private Pet pet;

    public void start() {
        JFrame frame = new JFrame("Pet Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);

        GameState savedGame = Save.loadGame();

        if (savedGame != null) {
            player = savedGame.player;
            pet = savedGame.pet;

            if (pet.getName() == null || pet.getName().isBlank()) {
                pet = new Pet(askForPetName(frame));
            }
        } else {
            player = new Player();
            pet = new Pet(askForPetName(frame));
        }

        PlayerStats playerStats = new PlayerStats();

        PetSprite sprite = new PetSprite();
        PetDashboard dashboard = new PetDashboard(pet, player, playerStats, sprite);

        playerStats.setPreferredSize(new Dimension(0, 50));
        dashboard.setPreferredSize(new Dimension(0, 330));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(playerStats, BorderLayout.NORTH);
        mainPanel.add(sprite, BorderLayout.CENTER);
        mainPanel.add(dashboard, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        frame.add(mainPanel);
        playerStats.updateStats(player);
        frame.setVisible(true);

        // Every timer tick changes the game state and refreshes the visible values.
        Timer timer = new Timer(10000, (ActionEvent e) -> {
            pet.passTime();
            dashboard.updateDashboard();

            if (pet.isAlive()) {
                player.passTime();
                playerStats.updateStats(player);
            }

        });
        timer.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Save.saveGame(player, pet);
            }
        });
    }

    private String askForPetName(JFrame frame) {
        String petName = JOptionPane.showInputDialog(frame, "Name your pet:");

        if (petName == null || petName.isBlank()) {
            return "Pet";
        }

        return petName.trim();
    }
}
