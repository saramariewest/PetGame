package ui;

import java.awt.event.*;
import javax.swing.*;

public class PetGameMenu {

  private final JMenuBar menuBar;
  private final JMenuItem highscoreItem;
  private final JMenuItem settingsItem;
  private final JMenuItem mainMenuItem;

  public PetGameMenu(JFrame frame) {
    menuBar = new JMenuBar();

    JMenu menu = new JMenu("Menu");
    menu.setMnemonic(KeyEvent.VK_M);

    menuBar.add(menu);
    frame.setJMenuBar(menuBar);

    highscoreItem = new JMenuItem("Highscore");
    highscoreItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2,
        InputEvent.ALT_DOWN_MASK));
    menu.add(highscoreItem);

    settingsItem = new JMenuItem("Settings");
    settingsItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S,
        InputEvent.ALT_DOWN_MASK));
    menu.add(settingsItem);

    mainMenuItem = new JMenuItem("Main Menu");
    mainMenuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_1,
        InputEvent.ALT_DOWN_MASK));
    menu.add(mainMenuItem);
  }

  public void onHighscore(Runnable action) {
    highscoreItem.addActionListener(e -> action.run());
  }

  public void onSettings(Runnable action) {
    settingsItem.addActionListener(e -> action.run());
  }

  public void onMainMenu(Runnable action) {
    mainMenuItem.addActionListener(e -> action.run());
  }
}
