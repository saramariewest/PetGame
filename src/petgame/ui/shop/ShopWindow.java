package petgame.ui.shop;

import java.awt.*;
import javax.swing.*;
import petgame.domain.item.Item;
import petgame.domain.player.Player;
import petgame.ui.dashboard.PlayerStats;
import petgame.ui.UiTheme;

// Lets the player buy items with coins.
public class ShopWindow {

    private final JFrame shop;
    private final JPanel foodPanel;
    private final JPanel drinkPanel;
    private final JPanel toyPanel;

    public ShopWindow(Player player, PlayerStats playerStats, Runnable onPurchase) {
        shop = new JFrame("Shop");
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(600, 400);
        shop.setLocationRelativeTo(null);

        JPanel content = new JPanel(new GridLayout(3, 1, 12, 12));
        content.setBackground(UiTheme.BACKGROUND);
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        shop.setContentPane(content);

        foodPanel = new JPanel();
        drinkPanel = new JPanel();
        toyPanel = new JPanel();

        foodPanel.setBackground(UiTheme.SURFACE);
        foodPanel.setBorder(UiTheme.createSectionBorder("Food"));
        foodPanel.setLayout(new GridLayout(1, 3));
        drinkPanel.setBackground(UiTheme.SURFACE);
        drinkPanel.setBorder(UiTheme.createSectionBorder("Drinks"));
        drinkPanel.setLayout(new GridLayout(1, 3));
        toyPanel.setBackground(UiTheme.SURFACE);
        toyPanel.setBorder(UiTheme.createSectionBorder("Toys"));
        toyPanel.setLayout(new GridLayout(1, 3));

        for (Item item : Item.values()) {
            JButton button = new JButton(item.displayName + " (" + item.price + ")");
            UiTheme.styleSecondaryButton(button);
            button.addActionListener(e -> {
                if (player.getCoins() >= item.price) {
                    player.setCoins(player.getCoins() - item.price);
                    int oldAmount = player.getInventory().getOrDefault(item, 0);
                    player.getInventory().put(item, oldAmount + 1);

                    onPurchase.run();
                    playerStats.updateStats(player);
                } else {
                    JOptionPane.showMessageDialog(shop, "Not enough coins for " + item.displayName + "!");
                }
            });

            switch (item.type) {
                case FOOD -> foodPanel.add(button);
                case DRINK -> drinkPanel.add(button);
                case TOY -> toyPanel.add(button);
            }
        }

        content.add(foodPanel);
        content.add(drinkPanel);
        content.add(toyPanel);

        shop.setVisible(true);
    }
}
