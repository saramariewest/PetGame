
import java.awt.*;
import javax.swing.*;

public class PetSupplies extends JPanel {

    private final JButton inventoryButton;
    private final JButton shopButton;

    public PetSupplies() {
        setLayout(new GridLayout(2, 1));

        inventoryButton = new JButton("Inventory");
        shopButton = new JButton("Shop");

        shopButton.addActionListener(e -> new PetShop());

        add(inventoryButton);
        add(shopButton);
    }
}
