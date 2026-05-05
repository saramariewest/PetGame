import java.awt.*;
import javax.swing.*;

public class PetGame {
  public PetGame() {
    JFrame frame = new JFrame("Pet Game");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 600);
    frame.setLayout(new GridLayout(2, 2));

    Pet pet = new Pet();
    Status statusPanel = new Status();
    Actions actionsPanel = new Actions();

    frame.add(new JLabel("Pet Status"));
    frame.add(new JLabel("Actions"));
    frame.add(statusPanel);
    frame.add(actionsPanel);

    frame.setVisible(true);
  }
}
