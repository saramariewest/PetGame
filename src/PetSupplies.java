import java.awt.*;
import javax.swing.*;

// This panel opens the inventory and shop windows.
public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;
    private final Player player;
    private final PlayerStats playerStats;

    public PetSupplies(Player player, PlayerStats playerStats) {
        this.player = player;
        this.playerStats = playerStats;
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

        // Both buttons open a separate window.
        inventoryButton.addActionListener(e -> new PetInventory(player));
        shopButton.addActionListener(e -> new PetShop(player, playerStats));

        add(inventoryButton);
        add(shopButton);
    }
}
