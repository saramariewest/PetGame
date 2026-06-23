package persistence;

import java.io.*;
import java.time.Instant;

import model.Pet;
import model.Player;

public class GameSaveStore {
    public static void saveGame(Player player, Pet pet) {
        GameState object = new GameState(player, pet);
        String filename = "petgame.ser";
        player.setSaveTime(Instant.now().toEpochMilli());
        pet.setSaveTime(Instant.now().toEpochMilli());

        // Write the current game state to disk.
        try (FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(object);
            System.out.println("Game has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public static GameState loadGame() {
        GameState object = null;
        String filename = "petgame.ser";

        // Read the saved game state from disk.
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
