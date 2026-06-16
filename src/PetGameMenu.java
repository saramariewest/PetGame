import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PetGameMenu extends JFrame {

  private final JMenuBar menuBar;

  public PetGameMenu(JFrame frame) {
    menuBar = new JMenuBar();

    JMenu menu = new JMenu("Menu");
    menu.setMnemonic(KeyEvent.VK_M);

    menuBar.add(menu);
    frame.setJMenuBar(menuBar);

    JMenuItem petItem = new JMenuItem("Pet");
    petItem.setMnemonic(KeyEvent.VK_P);
    petItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_1,
        InputEvent.ALT_DOWN_MASK));

    petItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Pet Menü geöffnet"));

    menu.add(petItem);

    menu.addSeparator();

    JMenu playerMenu = new JMenu("Player");
    playerMenu.setMnemonic(KeyEvent.VK_L);

    JMenuItem highscoreItem = new JMenuItem("Highscore");
    highscoreItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2,
        InputEvent.ALT_DOWN_MASK));

    highscoreItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Highscore geöffnet"));

    JMenuItem settingsItem = new JMenuItem("Settings");
    settingsItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Settings geöffnet"));

    playerMenu.add(highscoreItem);
    playerMenu.add(settingsItem);

    menu.add(playerMenu);
  }
}
