import javax.swing.*;

public class Status extends JPanel {
  private final JProgressBar hungerBar;
  private final JProgressBar thirstBar;
  private final JProgressBar moodBar;
  private final JProgressBar energyBar;

  public Status() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    hungerBar = new JProgressBar(0, 100);
    thirstBar = new JProgressBar(0, 100);
    moodBar = new JProgressBar(0, 100);
    energyBar = new JProgressBar(0, 100);

    add(new JLabel("Hunger"));
    add(hungerBar);
    add(new JLabel("Thirst"));
    add(thirstBar);
    add(new JLabel("Mood"));
    add(moodBar);
    add(new JLabel("Energy"));
    add(energyBar);
  }
}
