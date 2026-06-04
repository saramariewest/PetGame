import javax.swing.*;

// Progress bars make the pet values easier to read than plain numbers.
public class PetStats extends JPanel {

    private final JProgressBar hungerBar;
    private final JProgressBar thirstBar;
    private final JProgressBar moodBar;
    private final JProgressBar energyBar;
    private final JLabel levelLabel;
    private final JLabel stageLabel;
    private final JProgressBar experienceBar;

    public PetStats() {
        levelLabel = new JLabel("Level: 1");
        stageLabel = new JLabel("Stage: Baby");
        hungerBar = new JProgressBar(0, 100);
        thirstBar = new JProgressBar(0, 100);
        moodBar = new JProgressBar(0, 100);
        energyBar = new JProgressBar(0, 100);
        experienceBar = new JProgressBar(0, 25);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(levelLabel);
        add(stageLabel);
        add(Box.createVerticalStrut(10));
        add(new JLabel("XP"));
        add(Box.createVerticalStrut(5));
        add(experienceBar);
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

    // Swing components do not update automatically when the pet object changes.
    public void updateStats(Pet pet) {
        levelLabel.setText("Level: " + pet.getLevel());
        stageLabel.setText("Stage: " + pet.getEvolutionStage().displayName);
        experienceBar.setMaximum(pet.getExperienceForNextLevel());
        experienceBar.setValue(pet.getExperience());
        hungerBar.setValue(pet.getHunger());
        thirstBar.setValue(pet.getThirst());
        moodBar.setValue(pet.getMood());
        energyBar.setValue(pet.getEnergy());
    }
}
