import java.awt.*;
import javax.swing.*;

public class PetDashboard extends JPanel {
  private final Pet pet;
  private final PetStats statsPanel;
  private final PetSupplies suppliesPanel;
  private final PetActions actionsPanel;

  public PetDashboard(Pet pet) {
    this.pet = pet;
    setLayout(new BorderLayout(5, 5));

    statsPanel = new PetStats();
    suppliesPanel = new PetSupplies();
    actionsPanel = new PetActions();

    JPanel rightPanel = new JPanel(new GridLayout(1, 2, 0, 5));
    rightPanel.add(suppliesPanel);
    rightPanel.add(actionsPanel);

    statsPanel.setPreferredSize(new Dimension(250, 0));

    add(statsPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.CENTER);

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
