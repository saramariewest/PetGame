import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PetGame {
  public PetGame() {
    JFrame frame = new JFrame("Pet Game");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 600);
    frame.setLayout(new GridLayout(2, 1));

    Pet pet = new Pet();
    PetSprite sprite = new PetSprite();
    PetDashboard dashboard = new PetDashboard(pet);

    frame.add(sprite);
    frame.add(dashboard);
    frame.setVisible(true);

    Timer timer = new Timer(1000, (ActionEvent e) -> {
        pet.passTime();
        dashboard.updateDashboard();
    });
    timer.start();
  }
}
