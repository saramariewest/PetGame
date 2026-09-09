package petgame.ui.inventory;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import petgame.domain.item.Item;
import petgame.domain.player.Player;
import petgame.ui.UiTheme;

// Shows the amount owned for each item.
public class InventoryWindow {

  private final JFrame inventory;
  private final JPanel foodPanel;
  private final JPanel drinkPanel;
  private final JPanel toyPanel;
  private final Player player;
  private final EnumMap<Item, JLabel> countLabels = new EnumMap<>(Item.class);

  public InventoryWindow(Player player) {
    this.player = player;
    inventory = new JFrame("Inventory");
    inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inventory.setSize(600, 400);
    inventory.setLocationRelativeTo(null);
    JPanel content = new JPanel(new GridLayout(3, 1, 12, 12));
    content.setBackground(UiTheme.BACKGROUND);
    content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    inventory.setContentPane(content);

    foodPanel = new JPanel();
    foodPanel.setBackground(UiTheme.SURFACE);
    foodPanel.setBorder(UiTheme.createSectionBorder("Food"));
    foodPanel.setLayout(new GridLayout(1, Item.values().length));
    drinkPanel = new JPanel();
    drinkPanel.setBackground(UiTheme.SURFACE);
    drinkPanel.setBorder(UiTheme.createSectionBorder("Drinks"));
    drinkPanel.setLayout(new GridLayout(1, Item.values().length));
    toyPanel = new JPanel();
    toyPanel.setBackground(UiTheme.SURFACE);
    toyPanel.setBorder(UiTheme.createSectionBorder("Toys"));
    toyPanel.setLayout(new GridLayout(1, Item.values().length));

    for (Item item : Item.values()) {
      JPanel panel = new JPanel(new BorderLayout());
      panel.setBackground(UiTheme.SURFACE_LIGHT);
      panel.setBorder(UiTheme.createSectionBorder(item.displayName));
      JLabel count = new JLabel(String.valueOf(player.getInventory().getOrDefault(item, 0)), SwingConstants.CENTER);
      count.setFont(UiTheme.HEADING_FONT);
      count.setForeground(UiTheme.ACCENT);
      panel.add(count, BorderLayout.CENTER);
      countLabels.put(item, count);

      switch (item.type) {
        case FOOD -> foodPanel.add(panel);
        case DRINK -> drinkPanel.add(panel);
        case TOY -> toyPanel.add(panel);
      }

      player.getInventory().putIfAbsent(item, 0);
    }

    content.add(foodPanel);
    content.add(drinkPanel);
    content.add(toyPanel);
  }

  public void updateLabel(Item item) {
    JLabel lbl = countLabels.get(item);
    if (lbl != null) {
      SwingUtilities.invokeLater(() -> lbl.setText(String.valueOf(player.getInventory().getOrDefault(item, 0))));
    }
  }

  public void refresh() {
    for (Item item : Item.values()) {
      updateLabel(item);
    }
  }

  public void showInventory() {
    refresh();
    inventory.setVisible(true);
  }
}
