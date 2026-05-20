import java.awt.GridLayout;
import javax.swing.*;

public class PetShop {

    private final JFrame shop;
    private final JPanel foodPanel;
    private final JPanel drinkPanel;
    private final JPanel toyPanel;
    private final Player player;

    public PetShop(Player player) {
        this.player = player;
        shop = new JFrame("Shop");
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(400, 400);
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

        for (Items currentItem : Items.values()) {
            JButton button = new JButton(currentItem.displayName);
            button.addActionListener(e -> {
                if (player.getCoins() >= currentItem.price) {
                    player.setCoins(player.getCoins() - currentItem.price);
                    int old = player.getMapping().getOrDefault(currentItem, 0);
                    player.getMapping().put(currentItem, old + 1);

                    PetInventory inv = player.getInventoryWindow();
                    if (inv != null)
                        inv.updateLabel(currentItem);
                }
            });

            switch (currentItem.type) {
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
