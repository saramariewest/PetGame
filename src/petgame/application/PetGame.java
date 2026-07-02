package petgame.application;

import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import javax.swing.*;

import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;
import petgame.domain.pet.Pet;
import petgame.domain.player.Player;
import petgame.persistence.GameDataStore;
import petgame.ui.dashboard.PetDashboard;
import petgame.ui.dashboard.PetSprite;
import petgame.ui.dashboard.PlayerStats;
import petgame.ui.menu.GameMenuBar;
import petgame.ui.menu.StartMenuPanel;
import petgame.ui.highscore.HighscoreDialog;

// Coordinates the game flow, menus, and the main window.
public class PetGame {

    private static final int TICK_RATE = 10_000;
    private static final String MENU_CARD = "menu";
    private static final String GAME_CARD = "game";

    private final GameDataStore gameDataStore;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel rootPanel;
    private StartMenuPanel mainMenuPanel;
    private JPanel gamePanel;
    private Player player;
    private Pet pet;
    private PlayerStats playerStats;
    private PetSprite sprite;
    private PetDashboard dashboard;
    private GameMenuBar gameMenu;
    private Timer timer;
    private boolean highscoreRecorded;

    public PetGame(GameDataStore gameDataStore) {
        this.gameDataStore = gameDataStore;
    }

    public void start() {
        frame = new JFrame("Pet Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);

        mainMenuPanel = new StartMenuPanel();
        mainMenuPanel.onNewGame(this::startNewGame);
        mainMenuPanel.onSavedGame(this::startSavedGame);
        mainMenuPanel.onHighscore(this::showHighscores);
        mainMenuPanel.onSettings(this::showSettings);

        rootPanel.add(mainMenuPanel, MENU_CARD);
        frame.setContentPane(rootPanel);
        frame.setJMenuBar(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCurrentGame();
            }
        });

        cardLayout.show(rootPanel, MENU_CARD);
        frame.setVisible(true);
    }

    private void startNewGame() {
        stopCurrentGame();

        player = new Player();
        pet = new Pet(askForPetName());
        highscoreRecorded = false;

        showGameScreen();
    }

    private void startSavedGame() {
        stopCurrentGame();

        GameState savedGame = gameDataStore.loadGame().orElse(null);
        if (savedGame == null) {
            JOptionPane.showMessageDialog(frame, "No saved game found.");
            return;
        }

        player = savedGame.getPlayer();
        pet = savedGame.getPet();
        highscoreRecorded = pet.isHighscoreRecorded();

        long now = Instant.now().toEpochMilli();
        pet.passTime(now - pet.getSaveTime());
        player.passTime(now - player.getSaveTime());

        if (pet.getName() == null || pet.getName().isBlank()) {
            pet.setName("Pet");
        }

        showGameScreen();

        if (!pet.isAlive() && !highscoreRecorded) {
            recordHighscoreAtDeath();
        }
    }

    private void showGameScreen() {
        buildGameScreen();
        if (!rootPanel.isAncestorOf(gamePanel)) {
            rootPanel.add(gamePanel, GAME_CARD);
        }

        installGameMenuBar();
        frame.revalidate();
        frame.repaint();
        cardLayout.show(rootPanel, GAME_CARD);

        startTimer();
        playerStats.updateStats(player);
        dashboard.updateDashboard();
    }

    private void buildGameScreen() {
        playerStats = new PlayerStats();
        sprite = new PetSprite();
        dashboard = new PetDashboard(pet, player, playerStats, sprite);

        playerStats.setPreferredSize(new Dimension(0, 50));
        dashboard.setPreferredSize(new Dimension(0, 330));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(playerStats, BorderLayout.NORTH);
        mainPanel.add(sprite, BorderLayout.CENTER);
        mainPanel.add(dashboard, BorderLayout.SOUTH);
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gamePanel = mainPanel;
    }

    private void installGameMenuBar() {
        gameMenu = new GameMenuBar(frame);
        gameMenu.onHighscore(this::showHighscores);
        gameMenu.onSettings(this::showSettings);
        gameMenu.onMainMenu(this::returnToMainMenu);
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(TICK_RATE, (ActionEvent e) -> {
            pet.passTime(TICK_RATE);
            dashboard.updateDashboard();

            if (pet.isAlive()) {
                player.passTime(TICK_RATE);
                playerStats.updateStats(player);
            } else if (!highscoreRecorded) {
                recordHighscoreAtDeath();
            }
        });
        timer.start();
    }

    private void stopCurrentGame() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        if (gamePanel != null && rootPanel != null && rootPanel.isAncestorOf(gamePanel)) {
            rootPanel.remove(gamePanel);
        }

        gamePanel = null;
        playerStats = null;
        sprite = null;
        dashboard = null;
        gameMenu = null;

        if (frame != null) {
            frame.setJMenuBar(null);
            frame.revalidate();
            frame.repaint();
        }
    }

    private void returnToMainMenu() {
        saveCurrentGame();
        stopCurrentGame();
        cardLayout.show(rootPanel, MENU_CARD);
        frame.setJMenuBar(null);
        frame.revalidate();
        frame.repaint();
    }

    private void saveCurrentGame() {
        if (player != null && pet != null) {
            long saveTime = Instant.now().toEpochMilli();
            player.setSaveTime(saveTime);
            pet.setSaveTime(saveTime);
            gameDataStore.saveGame(new GameState(player, pet));
        }
    }

    private void showSettings() {
        JOptionPane.showMessageDialog(frame, "No settings available yet.");
    }

    private String askForPetName() {
        String petName = JOptionPane.showInputDialog(frame, "Name your pet:");

        if (petName == null || petName.isBlank()) {
            return "Pet";
        }

        return petName.trim();
    }

    private void recordHighscoreAtDeath() {
        highscoreRecorded = true;
        pet.setHighscoreRecorded(true);

        String name = JOptionPane.showInputDialog(
                frame,
                "Enter your name for the highscore:");

        if (name == null || name.isBlank()) {
            name = "Anonymous";
        }

        gameDataStore.addHighscore(
                new HighscoreEntry(name.trim(), pet.getLevel(), pet.getSurvivalTimeMillis()));
        showHighscores();
    }

    private void showHighscores() {
        HighscoreDialog.show(frame, gameDataStore.loadHighscores());
    }
}


