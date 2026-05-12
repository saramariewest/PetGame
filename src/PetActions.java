import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PetActions extends JPanel {

    private final JButton feedButton;
    private final JButton drinkButton;
    private final JButton playButton;
    private final JButton sleepButton;

    public PetActions() {
        setLayout(new GridLayout(2, 2));

        feedButton = new JButton("Feed");
        drinkButton = new JButton("Drink");
        playButton = new JButton("Play");
        sleepButton = new JButton("Sleep");

        add(feedButton);
        add(drinkButton);
        add(playButton);
        add(sleepButton);
    }

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
}
