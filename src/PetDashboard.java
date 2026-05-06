import java.awt.*;
import javax.swing.*;

public class PetDashboard extends JPanel {
  private final Pet pet;
  private final Status statusPanel;
  private final Actions actionsPanel;

  public PetDashboard(Pet pet) {
    this.pet = pet;
    setLayout(new GridLayout(1, 2));

    statusPanel = new Status();
    actionsPanel = new Actions();

    add(statusPanel);
    add(actionsPanel);

    actionsPanel.addFeedListener(e -> updatePet(pet::feed));
    actionsPanel.addDrinkListener(e -> updatePet(pet::drink));
    actionsPanel.addPlayListener(e -> updatePet(pet::play));
    actionsPanel.addSleepListener(e -> updatePet(pet::sleep));

    statusPanel.updateStatus(pet);
  }

  private void updatePet(Runnable action) {
    action.run();
    statusPanel.updateStatus(pet);
  }
}
