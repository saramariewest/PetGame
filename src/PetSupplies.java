import java.awt.*;
import javax.swing.*;

// PetSupplies is the small menu for everything related to items.
public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;

    public PetSupplies(Player player, PlayerStats playerStats) {
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

        // Inventory and shop are separate windows so the main game stays visible.
        inventoryButton.addActionListener(e -> {
            PetInventory inventory = new PetInventory(player);
            inventory.showInventory();
        });
        shopButton.addActionListener(e -> new PetShop(player, playerStats));

        add(inventoryButton);
        add(shopButton);
    }
}
