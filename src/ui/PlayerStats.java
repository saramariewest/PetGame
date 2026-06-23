package ui;

import java.awt.*;
import javax.swing.*;

import model.Player;

// Shows values that belong to the player.
public class PlayerStats extends JPanel {

    private final JLabel coinLabel;

    public PlayerStats() {
        setLayout(new GridLayout(1, 2));
        coinLabel = new JLabel("Coins:   0");
        add(coinLabel);
    }

    // Refresh after rewards or shop purchases.
    public void updateStats(Player player) {
        coinLabel.setText("Coins:   " + player.getCoins());
    }
}
