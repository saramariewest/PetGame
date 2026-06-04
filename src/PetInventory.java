import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;

// The inventory keeps labels for each item so single amounts can be refreshed.
public class PetInventory {

  private final JFrame inventory;
  private final JPanel foodPanel;
  private final JPanel drinkPanel;
  private final JPanel toyPanel;
  private final Player player;
  private final EnumMap<Items, JLabel> countLabels = new EnumMap<>(Items.class);

  public PetInventory(Player player) {
    this.player = player;
    inventory = new JFrame("Inventory");
    inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inventory.setSize(600, 400);
    inventory.setLocationRelativeTo(null);
    inventory.setLayout(new GridLayout(3, 1));

    foodPanel = new JPanel();
    foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
    foodPanel.setLayout(new GridLayout(1, Items.values().length));
    drinkPanel = new JPanel();
    drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
    drinkPanel.setLayout(new GridLayout(1, Items.values().length));
    toyPanel = new JPanel();
    toyPanel.setBorder(BorderFactory.createTitledBorder("Toys"));
    toyPanel.setLayout(new GridLayout(1, Items.values().length));

    for (Items item : Items.values()) {
      JPanel panel = new JPanel(new BorderLayout());
      panel.setBorder(BorderFactory.createTitledBorder(item.displayName));
      JLabel count = new JLabel(String.valueOf(player.getInventory().getOrDefault(item, 0)), SwingConstants.CENTER);
      panel.add(count, BorderLayout.CENTER);
      countLabels.put(item, count);

      switch (item.type) {
        case FOOD -> foodPanel.add(panel);
        case DRINK -> drinkPanel.add(panel);
        case TOY -> toyPanel.add(panel);
      }

      player.getInventory().putIfAbsent(item, 0);
    }

    inventory.add(foodPanel);
    inventory.add(drinkPanel);
    inventory.add(toyPanel);
  }

  public void updateLabel(Items item) {
    JLabel lbl = countLabels.get(item);
    if (lbl != null) {
      SwingUtilities.invokeLater(() -> lbl.setText(String.valueOf(player.getInventory().getOrDefault(item, 0))));
    }
  }

  public void showInventory() {
    player.setInventoryWindow(this);
    inventory.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        if (player.getInventoryWindow() == PetInventory.this) {
          player.setInventoryWindow(null);
        }
      }
    });

    inventory.setVisible(true);
  }
}
