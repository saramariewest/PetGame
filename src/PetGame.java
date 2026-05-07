
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PetGame {

    public PetGame() {
        JFrame frame = new JFrame("Pet Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        Pet pet = new Pet();
        PetSprite sprite = new PetSprite();
        PetDashboard dashboard = new PetDashboard(pet);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sprite, dashboard);
        splitPane.setEnabled(false);
        frame.add(splitPane);
        frame.setVisible(true);
        splitPane.setDividerLocation(0.6);

        Timer timer = new Timer(10000, (ActionEvent e) -> {
            pet.passTime();
            dashboard.updateDashboard();
        });
        timer.start();
    }
}
