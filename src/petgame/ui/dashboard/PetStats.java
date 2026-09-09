package petgame.ui.dashboard;

import java.awt.Font;
import javax.swing.*;
import petgame.domain.pet.Pet;
import petgame.ui.UiTheme;

// Shows the pet values as labels and progress bars.
public class PetStats extends JPanel {

    private final JProgressBar hungerBar;
    private final JProgressBar thirstBar;
    private final JProgressBar moodBar;
    private final JProgressBar energyBar;
    private final JLabel levelLabel;
    private final JLabel stageLabel;
    private final JLabel sectionTitle;
    private final JProgressBar experienceBar;

    public PetStats() {
        levelLabel = new JLabel("Level: 1");
        stageLabel = new JLabel("Stage: Baby");
        sectionTitle = UiTheme.createSectionTitle("Pet status");
        hungerBar = new JProgressBar(0, 100);
        thirstBar = new JProgressBar(0, 100);
        moodBar = new JProgressBar(0, 100);
        energyBar = new JProgressBar(0, 100);
        experienceBar = new JProgressBar(0, 25);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        UiTheme.styleCard(this);
        levelLabel.setFont(UiTheme.HEADING_FONT);
        stageLabel.setFont(UiTheme.BODY_FONT);
        stageLabel.setForeground(UiTheme.MUTED_TEXT);
        UiTheme.styleProgressBar(experienceBar, UiTheme.ACCENT);
        UiTheme.styleProgressBar(hungerBar, new java.awt.Color(242, 166, 166));
        UiTheme.styleProgressBar(thirstBar, new java.awt.Color(151, 203, 232));
        UiTheme.styleProgressBar(moodBar, new java.awt.Color(229, 176, 215));
        UiTheme.styleProgressBar(energyBar, new java.awt.Color(244, 217, 138));

        add(sectionTitle);
        add(Box.createVerticalStrut(8));
        add(levelLabel);
        add(stageLabel);
        add(Box.createVerticalStrut(10));
        add(createStatLabel("XP"));
        add(Box.createVerticalStrut(5));
        add(experienceBar);
        add(createStatLabel("Hunger"));
        add(Box.createVerticalStrut(5));
        add(hungerBar);
        add(createStatLabel("Thirst"));
        add(Box.createVerticalStrut(5));
        add(thirstBar);
        add(createStatLabel("Mood"));
        add(Box.createVerticalStrut(5));
        add(moodBar);
        add(createStatLabel("Energy"));
        add(Box.createVerticalStrut(5));
        add(energyBar);
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(UiTheme.MUTED_TEXT);
        return label;
    }

    // Refresh after the pet state changes.
    public void updateStats(Pet pet) {
        levelLabel.setText("Level: " + pet.getLevel());
        stageLabel.setText("Stage: " + pet.getEvolutionStage().displayName);
        experienceBar.setMaximum(pet.getExperienceForNextLevel());
        experienceBar.setValue(pet.getExperience());
        experienceBar.setString(pet.getExperience() + " / " + pet.getExperienceForNextLevel());
        hungerBar.setValue(pet.getHunger());
        hungerBar.setString(pet.getHunger() + "%");
        thirstBar.setValue(pet.getThirst());
        thirstBar.setString(pet.getThirst() + "%");
        moodBar.setValue(pet.getMood());
        moodBar.setString(pet.getMood() + "%");
        energyBar.setValue(pet.getEnergy());
        energyBar.setString(pet.getEnergy() + "%");
    }
}
