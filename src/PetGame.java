import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

// This class builds the main window of the game.
public class PetGame {

    public PetGame() {
        JFrame frame = new JFrame("Pet Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);

        Player player = new Player();
        PlayerStats playerStats = new PlayerStats();
        Pet pet = new Pet();

        PetSprite sprite = new PetSprite();
        PetDashboard dashboard = new PetDashboard(pet, player, playerStats);

        playerStats.setPreferredSize(new Dimension(0, 50));
        dashboard.setPreferredSize(new Dimension(0, 250));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(playerStats, BorderLayout.NORTH);
        mainPanel.add(sprite, BorderLayout.CENTER);
        mainPanel.add(dashboard, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        frame.add(mainPanel);
        playerStats.updateStats(player);
        frame.setVisible(true);

        // The timer is the simple game loop of this project.
        Timer timer = new Timer(5000, (ActionEvent e) -> {
            pet.passTime();
            dashboard.updateDashboard();
            player.passTime();
            playerStats.updateStats(player);

        });
        timer.start();
    }
}
