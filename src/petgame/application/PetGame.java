package petgame.application;

import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import javax.swing.*;
import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;
import petgame.domain.pet.Pet;
import petgame.domain.player.Player;
import petgame.persistence.GameDataStore;
import petgame.ui.dashboard.PetDashboard;
import petgame.ui.dashboard.PetSprite;
import petgame.ui.dashboard.PlayerStats;
import petgame.ui.highscore.HighscoreDialog;
import petgame.ui.menu.GameMenuBar;
import petgame.ui.menu.StartMenuPanel;
import petgame.ui.UiTheme;

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
    private GameState currentGame;

    public PetGame(GameDataStore gameDataStore) {
        this.gameDataStore = gameDataStore;
    }

    public void start() {
        frame = new JFrame("Pet Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(920, 760);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);
        rootPanel.setBackground(UiTheme.BACKGROUND);

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

        String petName = askForPetName();
        if (petName == null) {
            return;
        }

        String saveName = askForSaveName(petName);
        if (saveName == null) {
            return;
        }

        player = new Player();
        pet = new Pet(petName);
        currentGame = new GameState(saveName, player, pet);
        highscoreRecorded = false;

        saveCurrentGame();

        showGameScreen();
    }

    private void startSavedGame() {
        stopCurrentGame();

        GameState savedGame = chooseSavedGame();
        if (savedGame == null) {
            return;
        }

        currentGame = savedGame;
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
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(UiTheme.BACKGROUND);
        mainPanel.add(playerStats, BorderLayout.NORTH);
        mainPanel.add(dashboard, BorderLayout.CENTER);
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gamePanel = mainPanel;
    }

    private void installGameMenuBar() {
        gameMenu = new GameMenuBar(frame);
        gameMenu.onSaveGame(this::saveCurrentGame);
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

            if (currentGame == null) {
                currentGame = new GameState(defaultSaveName(), player, pet);
            } else {
                currentGame.setPlayer(player);
                currentGame.setPet(pet);
            }

            gameDataStore.saveGame(currentGame);
        }
    }

    private String askForSaveName(String petName) {
        String defaultName = petName + "'s save";
        String saveName = JOptionPane.showInputDialog(frame, "Name this save slot:", defaultName);
        if (saveName == null) {
            return null;
        }
        return saveName.isBlank() ? defaultName : saveName.trim();
    }

    private String defaultSaveName() {
        String petName = pet == null || pet.getName() == null || pet.getName().isBlank() ? "Pet" : pet.getName();
        return petName + "'s save";
    }

    private GameState chooseSavedGame() {
        List<GameState> saves = gameDataStore.loadAllGames();
        if (saves.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No saved games found.");
            return null;
        }

        DefaultListModel<GameState> model = new DefaultListModel<>();
        saves.forEach(model::addElement);
        JList<GameState> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setCellRenderer(new SaveSlotRenderer());

        while (true) {
            Object[] options = { "Load", "Delete", "Cancel" };
            int choice = JOptionPane.showOptionDialog(
                    frame,
                    new JScrollPane(list),
                    "Choose a saved game",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            GameState selected = list.getSelectedValue();
            if (choice == 0 && selected != null) {
                UUID id = selected.getId();
                return id == null ? selected : gameDataStore.loadGame(id).orElse(null);
            }
            if (choice != 1 || selected == null) {
                return null;
            }

            int confirmation = JOptionPane.showConfirmDialog(
                    frame,
                    "Delete the save slot '" + selected.getSaveName() + "'?",
                    "Delete saved game",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirmation == JOptionPane.YES_OPTION) {
                gameDataStore.deleteGame(selected.getId());
                model.removeElement(selected);
                if (model.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No saved games found.");
                    return null;
                }
                list.setSelectedIndex(0);
            }
        }
    }

    private static class SaveSlotRenderer extends DefaultListCellRenderer {
        private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof GameState game) {
                String petName = game.getPet() == null || game.getPet().getName() == null
                        ? "Pet"
                        : game.getPet().getName();
                String savedAt = game.getSavedAt() == null ? "unknown time" : TIME_FORMAT.format(game.getSavedAt());
                label.setText(game.getSaveName() + " — " + petName + " (saved " + savedAt + ")");
            }
            return label;
        }
    }

    private void showSettings() {
        JOptionPane.showMessageDialog(frame, "No settings available yet.");
    }

    private String askForPetName() {
        String petName = JOptionPane.showInputDialog(frame, "Name your pet:");

        if (petName == null) {
            return null;
        }
        if (petName.isBlank()) {
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
