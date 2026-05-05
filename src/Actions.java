import java.awt.*;
import javax.swing.*;

public class Actions extends JPanel {
  private final JButton feedButton;
  private final JButton drinkButton;
  private final JButton playButton;
  private final JButton sleepButton;

  public Actions() {
    setLayout(new GridLayout(2, 2));

    feedButton = new JButton("Feed");
    drinkButton = new JButton("Drink");
    playButton = new JButton("Play");
    sleepButton = new JButton("Sleep");

    add(feedButton);
    add(drinkButton);
    add(playButton);
    add(sleepButton);
  }
}
