package petgame.ui.dashboard;

import java.awt.*;
import javax.swing.*;
import petgame.domain.player.Player;
import petgame.ui.UiTheme;

// Shows values that belong to the player.
public class PlayerStats extends JPanel {

    private final JLabel coinLabel;

    public PlayerStats() {
        setLayout(new BorderLayout());
        UiTheme.styleCard(this);
        coinLabel = new JLabel("Coins: 0");
        coinLabel.setFont(UiTheme.HEADING_FONT);
        coinLabel.setForeground(UiTheme.WARNING);
        add(coinLabel);
    }

    // Refresh after rewards or shop purchases.
    public void updateStats(Player player) {
        coinLabel.setText("Coins: " + player.getCoins());
    }
}
