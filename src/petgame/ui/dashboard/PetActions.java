package petgame.ui.dashboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import petgame.ui.UiTheme;

// Contains the main action buttons for the pet.
public class PetActions extends JPanel {

    private final JButton feedButton;
    private final JButton drinkButton;
    private final JButton playButton;
    private final JButton sleepButton;

    public PetActions() {
        setLayout(new BorderLayout(0, 10));
        UiTheme.styleCard(this);

        feedButton = new JButton("Feed");
        drinkButton = new JButton("Drink");
        playButton = new JButton("Play");
        sleepButton = new JButton("Sleep");

        UiTheme.stylePrimaryButton(feedButton);
        UiTheme.stylePrimaryButton(drinkButton);
        UiTheme.styleWarningButton(playButton);
        UiTheme.styleSecondaryButton(sleepButton);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(feedButton);
        buttonPanel.add(drinkButton);
        buttonPanel.add(playButton);
        buttonPanel.add(sleepButton);

        add(UiTheme.createSectionTitle("Care actions"), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // The dashboard connects these buttons to game behavior.
    public void addFeedListener(ActionListener l) {
        feedButton.addActionListener(l);
    }

    public void addDrinkListener(ActionListener l) {
        drinkButton.addActionListener(l);
    }

    public void addPlayListener(ActionListener l) {
        playButton.addActionListener(l);
    }

    public void addSleepListener(ActionListener l) {
        sleepButton.addActionListener(l);
    }

    public void setActionsEnabled(boolean enabled) {
        feedButton.setEnabled(enabled);
        drinkButton.setEnabled(enabled);
        playButton.setEnabled(enabled);
        sleepButton.setEnabled(enabled);
    }
}
