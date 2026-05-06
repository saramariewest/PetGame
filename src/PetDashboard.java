import java.awt.*;
import javax.swing.*;

public class PetDashboard extends JPanel {
  private final Pet pet;
  private final PetStats statsPanel;
  private final PetSupplies suppliesPanel;
  private final PetActions actionsPanel;

  public PetDashboard(Pet pet) {
    this.pet = pet;
    setLayout(new GridLayout(1, 2));

    statsPanel = new PetStats();
    suppliesPanel = new PetSupplies();
    actionsPanel = new PetActions();

    JPanel leftPanel = new JPanel(new BorderLayout(5,10));

    leftPanel.add(statsPanel, BorderLayout.NORTH);
    leftPanel.add(suppliesPanel, BorderLayout.CENTER);

    add(leftPanel);
    add(actionsPanel);

    actionsPanel.addFeedListener(e -> updatePet(pet::feed));
    actionsPanel.addDrinkListener(e -> updatePet(pet::drink));
    actionsPanel.addPlayListener(e -> updatePet(pet::play));
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
