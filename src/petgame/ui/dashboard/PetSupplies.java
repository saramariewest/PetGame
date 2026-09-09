package petgame.ui.dashboard;

import java.awt.*;
import javax.swing.*;
import petgame.domain.player.Player;
import petgame.ui.inventory.InventoryWindow;
import petgame.ui.shop.ShopWindow;
import petgame.ui.UiTheme;

// Contains item-related actions.
public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;
    private InventoryWindow inventoryWindow;

    public PetSupplies(Player player, PlayerStats playerStats) {
        setLayout(new BorderLayout(0, 10));
        UiTheme.styleCard(this);

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");
        UiTheme.styleSecondaryButton(inventoryButton);
        UiTheme.styleWarningButton(shopButton);

        // Keep inventory and shop in separate windows.
        inventoryButton.addActionListener(e -> {
            if (inventoryWindow == null) {
                inventoryWindow = new InventoryWindow(player);
            }
            inventoryWindow.showInventory();
        });
        shopButton.addActionListener(e -> new ShopWindow(
                player,
                playerStats,
                () -> {
                    if (inventoryWindow != null) {
                        inventoryWindow.refresh();
                    }
                }));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 8));
        buttonPanel.setOpaque(false);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(shopButton);

        add(UiTheme.createSectionTitle("Items"), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
