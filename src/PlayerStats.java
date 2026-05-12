import java.awt.*;
import javax.swing.*;

public class PlayerStats extends JPanel {

    private final JLabel coinLabel;

    public PlayerStats() {
        setLayout(new GridLayout(1, 2));
        coinLabel = new JLabel("Coins:   0");
        add(coinLabel);
    }

    public void updateStats(Player player) {
        coinLabel.setText("Coins:   " + player.getCoins());
    }
}
