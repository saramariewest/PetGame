package ui;

import java.awt.*;
import javax.swing.*;

import model.*;

// Contains item-related actions.
public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;

    public PetSupplies(Player player, PlayerStats playerStats) {
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

        // Keep inventory and shop in separate windows.
        inventoryButton.addActionListener(e -> {
            PetInventory inventory = new PetInventory(player);
            inventory.showInventory();
        });
        shopButton.addActionListener(e -> new PetShop(player, playerStats));

        add(inventoryButton);
        add(shopButton);
    }
}
