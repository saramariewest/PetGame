import java.awt.*;
import javax.swing.*;

public class PetDashboard extends JPanel {

    private final Pet pet;
    private final PetStats statsPanel;
    private final PetSupplies suppliesPanel;
    private final PetActions actionsPanel;
    private final JPanel leftPanel;
    private final Player player;

    public PetDashboard(Pet pet, Player player) {
        this.pet = pet;
        this.player = player;
        setLayout(new GridLayout(1, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsPanel = new PetStats();
        suppliesPanel = new PetSupplies(player);
        actionsPanel = new PetActions(player.getMapping());

        leftPanel = new JPanel(new BorderLayout(5, 10));

        leftPanel.add(statsPanel, BorderLayout.NORTH);
        leftPanel.add(suppliesPanel, BorderLayout.CENTER);

        add(leftPanel);
        add(actionsPanel);

        actionsPanel.addFeedListener(e -> {
            if (player.useItem(Type.FOOD)) {
                updatePet(pet::feed);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No food in inventory!");
            }
        });

        actionsPanel.addDrinkListener(e -> {
            if (player.useItem(Type.DRINK)) {
                updatePet(pet::drink);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No drinks in inventory!");
            }
        });

        actionsPanel.addPlayListener(e -> {
            if (player.useItem(Type.TOY)) {
                updatePet(pet::play);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No toys in inventory!");
            }
        });
        actionsPanel.addSleepListener(e -> updatePet(pet::sleep));

        statsPanel.updateStats(pet);
    }

    private void updatePet(Runnable action) {
        action.run();
        statsPanel.updateStats(pet);
    }

    public void updateDashboard() {
        statsPanel.updateStats(pet);
    }
}
