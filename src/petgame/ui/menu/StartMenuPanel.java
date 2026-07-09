package petgame.ui.menu;

import java.awt.*;
import javax.swing.*;

public class StartMenuPanel extends JPanel {

    private final JButton newGameButton;
    private final JButton savedGameButton;
    private final JButton highscoreButton;
    private final JButton settingsButton;

    public StartMenuPanel() {
        setLayout(new GridLayout(0, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 120, 30, 120));

        JLabel titleLabel = new JLabel("Pet Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        newGameButton = new JButton("New Game");
        savedGameButton = new JButton("Saved Game");
        highscoreButton = new JButton("Highscore");
        settingsButton = new JButton("Settings");

        newGameButton.setPreferredSize(new Dimension(0, 42));
        savedGameButton.setPreferredSize(new Dimension(0, 42));
        highscoreButton.setPreferredSize(new Dimension(0, 42));
        settingsButton.setPreferredSize(new Dimension(0, 42));

        add(titleLabel);
        add(newGameButton);
        add(savedGameButton);
        add(highscoreButton);
        add(settingsButton);
    }

    public void onNewGame(Runnable action) {
        newGameButton.addActionListener(e -> action.run());
    }

    public void onSavedGame(Runnable action) {
        savedGameButton.addActionListener(e -> action.run());
    }

    public void onHighscore(Runnable action) {
        highscoreButton.addActionListener(e -> action.run());
    }

    public void onSettings(Runnable action) {
        settingsButton.addActionListener(e -> action.run());
    }
}
