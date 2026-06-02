import java.awt.*;
import javax.swing.*;

// PlayerStats is separated from PetStats because coins belong to the player.
public class PlayerStats extends JPanel {

    private final JLabel coinLabel;

    public PlayerStats() {
        setLayout(new GridLayout(1, 2));
        coinLabel = new JLabel("Coins:   0");
        add(coinLabel);
    }

    // The label must be refreshed after timer rewards or shop purchases.
    public void updateStats(Player player) {
        coinLabel.setText("Coins:   " + player.getCoins());
    }
}
