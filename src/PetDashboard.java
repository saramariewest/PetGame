import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

// The dashboard is where button clicks are connected to pet and player data.
public class PetDashboard extends JPanel {

    private final Pet pet;
    private final PetStats statsPanel;
    private final PetSupplies suppliesPanel;
    private final PetActions actionsPanel;
    private final JPanel leftPanel;
    private final Player player;

    public PetDashboard(Pet pet, Player player, PlayerStats playerStats) {
        this.pet = pet;
        this.player = player;
        setLayout(new GridLayout(1, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsPanel = new PetStats();
        suppliesPanel = new PetSupplies(player, playerStats);
        actionsPanel = new PetActions();

        leftPanel = new JPanel(new BorderLayout(5, 10));

        leftPanel.add(statsPanel, BorderLayout.NORTH);
        leftPanel.add(suppliesPanel, BorderLayout.CENTER);

        add(leftPanel);
        add(actionsPanel);

        actionsPanel.addFeedListener(e -> {
            Items item = selectItem(Type.FOOD, "Choose food");
            if (item != null && player.useItem(item)) {
                pet.feed(item.points);
                statsPanel.updateStats(pet);
            }
        });

        actionsPanel.addDrinkListener(e -> {
            Items item = selectItem(Type.DRINK, "Choose drink");
            if (item != null && player.useItem(item)) {
                pet.drink(item.points);
                statsPanel.updateStats(pet);
            }
        });

        actionsPanel.addPlayListener(e -> {
            Items item = selectItem(Type.TOY, "Choose toy");
            if (item != null && player.useItem(item)) {
                pet.play(item.points);
                statsPanel.updateStats(pet);
            }
        });
        actionsPanel.addSleepListener(e -> {
            pet.sleep();
            statsPanel.updateStats(pet);
        });

        statsPanel.updateStats(pet);
    }

    private Items selectItem(Type type, String title) {
        // For example, feeding should only offer food items that are owned.
        Items[] availableItems = Arrays.stream(Items.values())
                .filter(item -> item.type == type)
                .filter(item -> player.getInventory().getOrDefault(item, 0) > 0)
                .toArray(Items[]::new);

        if (availableItems.length == 0) {
            JOptionPane.showMessageDialog(this, "No " + type.name().toLowerCase() + " in inventory!");
            return null;
        }

        return (Items) JOptionPane.showInputDialog(
                this,
                "Which item do you want to use?",
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                availableItems,
                availableItems[0]);
    }

    public void updateDashboard() {
        statsPanel.updateStats(pet);
    }
}
