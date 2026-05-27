import java.awt.*;
import javax.swing.*;

// This panel shows information about the player.
public class PlayerStats extends JPanel {

    private final JLabel coinLabel;

    public PlayerStats() {
        setLayout(new GridLayout(1, 2));
        coinLabel = new JLabel("Coins:   0");
        add(coinLabel);
    }

    // Refresh the coin label after the player earns or spends coins.
    public void updateStats(Player player) {
        coinLabel.setText("Coins:   " + player.getCoins());
    }
}
