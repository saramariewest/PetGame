import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// The dashboard is where button clicks are connected to pet and player data.
public class PetDashboard extends JPanel {

    private final Pet pet;
    private final PetStats statsPanel;
    private final PetSupplies suppliesPanel;
    private final PetActions actionsPanel;
    private final JPanel leftPanel;
    private final Player player;
    private final PetSprite sprite;

    public PetDashboard(Pet pet, Player player, PlayerStats playerStats, PetSprite sprite) {
        this.pet = pet;
        this.player = player;
        this.sprite = sprite;
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
            if (!pet.isAlive()) {
                return;
            }

            Items item = selectItem(Type.FOOD, "Choose food");
            if (item != null && player.useItem(item)) {
                pet.feed(item.points);
                pet.addExperience(5);
                updateDashboard();
            }
        });

        actionsPanel.addDrinkListener(e -> {
            if (!pet.isAlive()) {
                return;
            }

            Items item = selectItem(Type.DRINK, "Choose drink");
            if (item != null && player.useItem(item)) {
                pet.drink(item.points);
                pet.addExperience(4);
                updateDashboard();
            }
        });

        actionsPanel.addPlayListener(e -> {
            if (!pet.isAlive()) {
                return;
            }

            Items item = selectItem(Type.TOY, "Choose toy");
            if (item != null && player.useItem(item)) {
                pet.play(item.points);
                pet.addExperience(8);
                updateDashboard();
            }
        });
        actionsPanel.addSleepListener(e -> {
            if (!pet.isAlive()) {
                return;
            }

            pet.sleep();
            pet.addExperience(2);
            updateDashboard();
        });

        refreshDashboard();
    }

    private Items selectItem(Type type, String title) {
        // For example, feeding should only offer food items that are owned.
        ArrayList<Items> availableItems = new ArrayList<>();

        for (Items item : Items.values()) {
            int amount = player.getInventory().getOrDefault(item, 0);

            if (item.type == type && amount > 0) {
                availableItems.add(item);
            }
        }

        if (availableItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No " + type.name().toLowerCase() + " in inventory!");
            return null;
        }

        Object[] itemArray = availableItems.toArray();

        return (Items) JOptionPane.showInputDialog(
                this,
                "Which item do you want to use?",
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                itemArray,
                itemArray[0]);
    }

    public void updateDashboard() {
        refreshDashboard();
    }

    private void refreshDashboard() {
        statsPanel.updateStats(pet);
        sprite.updateSprite(pet);

        if (!pet.isAlive()) {
            actionsPanel.setActionsEnabled(false);
        }
    }
}
