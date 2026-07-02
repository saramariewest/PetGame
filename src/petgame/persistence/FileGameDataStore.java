package petgame.persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;

public class FileGameDataStore implements GameDataStore {

    private static final int MAX_HIGHSCORES = 10;
    private static final Comparator<HighscoreEntry> HIGHSCORE_ORDER =
            Comparator.comparingLong(HighscoreEntry::getScore).reversed()
                    .thenComparing(Comparator.comparingInt(HighscoreEntry::getLevel).reversed())
                    .thenComparing(Comparator.comparingLong(HighscoreEntry::getSurvivalTimeMillis).reversed());

    private final Path gameSavePath;
    private final Path highscorePath;

    public FileGameDataStore(Path gameSavePath, Path highscorePath) {
        this.gameSavePath = gameSavePath;
        this.highscorePath = highscorePath;
    }

    @Override
    public void saveGame(GameState gameState) {
        writeObject(gameSavePath, gameState);
    }

    @Override
    public Optional<GameState> loadGame() {
        return readObject(gameSavePath, GameState.class);
    }

    @Override
    public void addHighscore(HighscoreEntry entry) {
        List<HighscoreEntry> highscores = new ArrayList<>(loadHighscores());
        highscores.add(entry);
        highscores.sort(HIGHSCORE_ORDER);

        if (highscores.size() > MAX_HIGHSCORES) {
            highscores = new ArrayList<>(highscores.subList(0, MAX_HIGHSCORES));
        }

        writeObject(highscorePath, highscores);
    }

    @Override
    public List<HighscoreEntry> loadHighscores() {
        Optional<?> storedValue = readObject(highscorePath, List.class);
        if (storedValue.isEmpty()) {
            return new ArrayList<>();
        }

        List<HighscoreEntry> highscores = new ArrayList<>();
        for (Object entry : (List<?>) storedValue.get()) {
            if (entry instanceof HighscoreEntry highscoreEntry) {
                highscores.add(highscoreEntry);
            }
        }
        highscores.sort(HIGHSCORE_ORDER);
        return highscores;
    }

    private void writeObject(Path path, Object value) {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))) {
            output.writeObject(value);
        } catch (IOException exception) {
            System.err.println("Could not save data to " + path + ": " + exception.getMessage());
        }
    }

    private <T> Optional<T> readObject(Path path, Class<T> expectedType) {
        if (Files.notExists(path)) {
            return Optional.empty();
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path))) {
            Object value = input.readObject();
            if (expectedType.isInstance(value)) {
                return Optional.of(expectedType.cast(value));
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.err.println("Could not load data from " + path + ": " + exception.getMessage());
        }

        return Optional.empty();
    }
}
