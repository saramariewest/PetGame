package petgame.ui.menu;

import java.awt.*;
import javax.swing.*;
import petgame.ui.UiTheme;

public class StartMenuPanel extends JPanel {

    private final JButton newGameButton;
    private final JButton savedGameButton;
    private final JButton highscoreButton;
    private final JButton settingsButton;

    public StartMenuPanel() {
        setLayout(new GridBagLayout());
        setBackground(UiTheme.BACKGROUND);

        JLabel titleLabel = new JLabel("Pet Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(UiTheme.TITLE_FONT);
        titleLabel.setForeground(UiTheme.ACCENT);

        JLabel subtitleLabel = new JLabel("Care for your pet. Grow together.");
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        subtitleLabel.setFont(UiTheme.BODY_FONT);
        subtitleLabel.setForeground(UiTheme.MUTED_TEXT);

        newGameButton = new JButton("New Game");
        savedGameButton = new JButton("Saved Game");
        highscoreButton = new JButton("Highscore");
        settingsButton = new JButton("Settings");

        UiTheme.stylePrimaryButton(newGameButton);
        UiTheme.styleSecondaryButton(savedGameButton);
        UiTheme.styleSecondaryButton(highscoreButton);
        UiTheme.styleSecondaryButton(settingsButton);

        JPanel card = new JPanel(new GridLayout(4, 1, 0, 12));
        UiTheme.styleCard(card);
        card.add(newGameButton);
        card.add(savedGameButton);
        card.add(highscoreButton);
        card.add(settingsButton);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setPreferredSize(new Dimension(360, 350));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitleLabel);
        content.add(Box.createVerticalStrut(30));
        content.add(card);

        add(content);
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
