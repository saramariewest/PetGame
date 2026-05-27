import java.awt.GridLayout;
import javax.swing.*;

public class PetShop {

    private final JFrame shop;
    private final JPanel foodPanel;
    private final JPanel drinkPanel;
    private final JPanel toyPanel;
    private final Player player;
    private final PlayerStats playerStats;

    public PetShop(Player player, PlayerStats playerStats) {
        this.player = player;
        this.playerStats = playerStats;
        shop = new JFrame("Shop");
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(600, 400);
        shop.setLocationRelativeTo(null);

        shop.setLayout(new GridLayout(3, 1));

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
            JButton button = new JButton(item.displayName + " (" + item.price + ")");
            button.addActionListener(e -> {
                if (player.getCoins() >= item.price) {
                    player.setCoins(player.getCoins() - item.price);
                    int oldAmount = player.getInventory().getOrDefault(item, 0);
                    player.getInventory().put(item, oldAmount + 1);

                    PetInventory inv = player.getInventoryWindow();
                    if (inv != null)
                        inv.updateLabel(item);
                    playerStats.updateStats(player);
                } else {
                    JOptionPane.showMessageDialog(shop, "Not enough coins for " + item.displayName + "!");
                }
            });

            switch (item.type) {
                case FOOD -> foodPanel.add(button);
                case DRINK -> drinkPanel.add(button);
                case TOY -> toyPanel.add(button);
            }
        }

        shop.add(foodPanel);
        shop.add(drinkPanel);
        shop.add(toyPanel);

        shop.setVisible(true);
    }
}
