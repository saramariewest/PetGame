import java.awt.*;
import java.io.File;
import javax.swing.*;

// PetSprite displays the current sprite while Pet keeps the game logic.
public class PetSprite extends JPanel {

    private static final int SPRITE_SIZE = 320;
    private static final String PET_FOLDER = "cat";

    private final JLabel spriteLabel;

    public PetSprite() {
        setLayout(new GridBagLayout());
        spriteLabel = new JLabel("Pet Sprite Placeholder", SwingConstants.CENTER);
        spriteLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        spriteLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(spriteLabel);
    }

    public void updateSprite(Pet pet) {
        String fileName = pet.getEvolutionStage().spriteFile;
        File spriteFile = new File("src/assets/pets/" + PET_FOLDER + "/" + fileName);

        if (!spriteFile.exists()) {
            spriteLabel.setIcon(null);
            spriteLabel.setText(pet.getName());
            return;
        }

        ImageIcon icon = new ImageIcon(spriteFile.getPath());
        Image scaledImage = icon.getImage().getScaledInstance(SPRITE_SIZE, SPRITE_SIZE, Image.SCALE_SMOOTH);
        spriteLabel.setIcon(new ImageIcon(scaledImage));
        spriteLabel.setText(pet.getName());
    }
}
