import java.awt.*;
import javax.swing.*;

public class PetInventory {

  private final JFrame inventory;
  private final JPanel foodPanel;
  private final JPanel drinkPanel;
  private final JPanel toyPanel;

  public PetInventory() {
    inventory = new JFrame("Inventory");
    inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inventory.setSize(400, 400);
    inventory.setLocationRelativeTo(null);

    inventory.setLayout(new GridLayout(3, 1));

    foodPanel = new JPanel();
    drinkPanel = new JPanel();
    toyPanel = new JPanel();

    foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
    drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
    toyPanel.setBorder(BorderFactory.createTitledBorder("Toys"));

    inventory.setVisible(true);
  }
}
