
import java.awt.*;
import javax.swing.*;

public class PetShop {

    private final JFrame shop;
    private final JPanel foodPanel;
    private final JPanel drinkPanel;
    private final JPanel toyPanel;
    private final JButton cerealButton;
    private final JButton sushiButton;
    private final JButton cakeButton;
    private final JButton waterButton;
    private final JButton energyDrinkButton;
    private final JButton juiceButton;
    private final JButton ballButton;
    private final JButton skateboardButton;
    private final JButton consoleButton;

    public PetShop() {
        shop = new JFrame("Shop");
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(400, 300);
        shop.setLocationRelativeTo(null);

        shop.setLayout(new GridLayout(3, 1));

        foodPanel = new JPanel();
        drinkPanel = new JPanel();
        toyPanel = new JPanel();

        foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));
        drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
        toyPanel.setBorder(BorderFactory.createTitledBorder("Toys"));

        cerealButton = new JButton("Cereal");
        sushiButton = new JButton("Sushi");
        cakeButton = new JButton("Cake");
        waterButton = new JButton("Water");
        energyDrinkButton = new JButton("Energy Drink");
        juiceButton = new JButton("Juice");
        ballButton = new JButton("Ball");
        skateboardButton = new JButton("Skateboard");
        consoleButton = new JButton("Console");

        foodPanel.setLayout(new GridLayout(0, 3));
        foodPanel.add(cerealButton);
        foodPanel.add(sushiButton);
        foodPanel.add(cakeButton);

        drinkPanel.setLayout(new GridLayout(0, 3));
        drinkPanel.add(waterButton);
        drinkPanel.add(energyDrinkButton);
        drinkPanel.add(juiceButton);

        toyPanel.setLayout(new GridLayout(0, 3));
        toyPanel.add(ballButton);
        toyPanel.add(skateboardButton);
        toyPanel.add(consoleButton);

        shop.add(foodPanel);
        shop.add(drinkPanel);
        shop.add(toyPanel);

        shop.setVisible(true);
    }
}
