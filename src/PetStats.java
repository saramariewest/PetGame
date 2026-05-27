import javax.swing.*;

// This panel shows the current values of the pet.
public class PetStats extends JPanel {

    private final JProgressBar hungerBar;
    private final JProgressBar thirstBar;
    private final JProgressBar moodBar;
    private final JProgressBar energyBar;

    public PetStats() {
        hungerBar = new JProgressBar(0, 100);
        thirstBar = new JProgressBar(0, 100);
        moodBar = new JProgressBar(0, 100);
        energyBar = new JProgressBar(0, 100);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Hunger"));
        add(Box.createVerticalStrut(5));
        add(hungerBar);
        add(new JLabel("Thirst"));
        add(Box.createVerticalStrut(5));
        add(thirstBar);
        add(new JLabel("Mood"));
        add(Box.createVerticalStrut(5));
        add(moodBar);
        add(new JLabel("Energy"));
        add(Box.createVerticalStrut(5));
        add(energyBar);
    }

    // Copy the values from the pet object into the progress bars.
    public void updateStats(Pet pet) {
        hungerBar.setValue(pet.getHunger());
        thirstBar.setValue(pet.getThirst());
        moodBar.setValue(pet.getMood());
        energyBar.setValue(pet.getEnergy());
    }
}
