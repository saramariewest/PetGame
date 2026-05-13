import java.awt.*;
import javax.swing.*;

public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;
    private final Player player;

    public PetSupplies(Player player) {
        this.player = player;
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

        inventoryButton.addActionListener(e -> new PetInventory(player));
        shopButton.addActionListener(e -> new PetShop(player));

        add(inventoryButton);
        add(shopButton);
    }
}
