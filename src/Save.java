import java.io.*;

class GameState implements Serializable {
    public Player player;
    public Pet pet;

    public GameState(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
    }
}

public class Save {
    public static void saveGame(Player player, Pet pet) {
        GameState object = new GameState(player, pet);
        String filename = "petgame.ser";

        // Serialization
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

        // Deserialization
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
