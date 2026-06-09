import java.io.*;

class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public Player player;
    public Pet pet;
    public long lastSavedAt;

    public GameState(Player player, Pet pet, long lastSavedAt) {
        this.player = player;
        this.pet = pet;
        this.lastSavedAt = lastSavedAt;
    }
}

public class Save {
    public static void saveGame(Player player, Pet pet, long lastSavedAt) {
        GameState object = new GameState(player, pet, lastSavedAt);
        String filename = "petgame.ser";

        try (FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(object);
            System.out.println("Game has been serialized");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    static GameState loadGame() {
        GameState object = null;
        String filename = "petgame.ser";

        try (FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file)) {
            object = (GameState) in.readObject();
            System.out.println("Game has been deserialized");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        return object;
    }
}
