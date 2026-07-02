package petgame;

import java.nio.file.Path;

import petgame.application.PetGame;
import petgame.persistence.FileGameDataStore;
import petgame.persistence.GameDataStore;

public class Main {

    public static void main(String[] args) {
        // Start the main game window.
        GameDataStore store = new FileGameDataStore(
                Path.of("petgame.ser"),
                Path.of("highscores.ser"));
        PetGame game = new PetGame(store);
        game.start();
    }
}


