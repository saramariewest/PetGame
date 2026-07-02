package petgame.ui.dashboard;

import java.awt.*;
import javax.swing.*;

import petgame.domain.player.Player;
import petgame.ui.inventory.InventoryWindow;
import petgame.ui.shop.ShopWindow;

// Contains item-related actions.
public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;
    private InventoryWindow inventoryWindow;

    public PetSupplies(Player player, PlayerStats playerStats) {
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

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

        add(inventoryButton);
        add(shopButton);
    }
}


