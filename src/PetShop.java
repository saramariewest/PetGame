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
        drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
        toyPanel.setBorder(BorderFactory.createTitledBorder("Toys"));

        for (Items item : Items.values()) {
            final Items currentItem = item;
            JButton button = new JButton(currentItem.displayName);
            button.addActionListener(e -> {
            });
            switch (item.type) {
                case FOOD -> foodPanel.add(button);
                case DRINK -> drinkPanel.add(button);
                case TOY -> toyPanel.add(button);
            }
            player.getMapping().put(currentItem, currentItem.displayName);
        }

        shop.add(foodPanel);
        shop.add(drinkPanel);
        shop.add(toyPanel);

        shop.setVisible(true);
    }
}
