import java.awt.*;
import javax.swing.*;

public class PetInventory {

  private final JFrame inventory;
  private final JPanel foodPanel;
  private final JPanel drinkPanel;
  private final JPanel toyPanel;
  private final Player player;

  public PetInventory(Player player) {
    this.player = player;
    inventory = new JFrame("Inventory");
    inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inventory.setSize(400, 400);
    inventory.setLocationRelativeTo(null);

    inventory.setLayout(new GridLayout(3, 1));

    foodPanel = new JPanel();
    drinkPanel = new JPanel();
    toyPanel = new JPanel();

    foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
    foodPanel.setLayout(new GridLayout(1, 3));
    drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
    drinkPanel.setLayout(new GridLayout(1, 3));
    toyPanel.setBorder(BorderFactory.createTitledBorder("Toys"));
    toyPanel.setLayout(new GridLayout(1, 3));

    for (Items item : Items.values()) {
      final Items currentItem = item;
      JPanel panel = new JPanel();
      panel.setBorder(BorderFactory.createTitledBorder(currentItem.displayName));
      switch (item.type) {
        case FOOD -> foodPanel.add(panel);
        case DRINK -> drinkPanel.add(panel);
        case TOY -> toyPanel.add(panel);
      }
      player.getMapping().put(currentItem, 0);
    }

    inventory.add(foodPanel);
    inventory.add(drinkPanel);
    inventory.add(toyPanel);

    inventory.setVisible(true);
  }
}
