package petgame.persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;

public class FileGameDataStore implements GameDataStore {

    private static final int MAX_HIGHSCORES = 10;
    private static final Comparator<HighscoreEntry> HIGHSCORE_ORDER = Comparator
            .comparingLong((HighscoreEntry entry) -> entry.getScore())
            .reversed()
            .thenComparing(
                    Comparator.comparingInt((HighscoreEntry entry) -> entry.getLevel()).reversed())
            .thenComparing(
                    Comparator.comparingLong((HighscoreEntry entry) -> entry.getSurvivalTimeMillis()).reversed());

    private final Path gameSavePath;
    private final Path highscorePath;

    public FileGameDataStore(Path gameSavePath, Path highscorePath) {
        this.gameSavePath = gameSavePath;
        this.highscorePath = highscorePath;
    }

    @Override
    public void saveGame(GameState gameState) {
        Map<UUID, GameState> games = readGameStore();
        if (gameState.getId() == null) {
            gameState.setId(UUID.randomUUID());
        }
        gameState.setSavedAt(java.time.LocalDateTime.now());
        games.put(gameState.getId(), gameState);
        writeObject(gameSavePath, games);
    }

    @Override
    public Optional<GameState> loadGame(UUID id) {
        return Optional.ofNullable(readGameStore().get(id));
    }

    @Override
    public List<GameState> loadAllGames() {
        List<GameState> games = new ArrayList<>(readGameStore().values());
        games.sort(Comparator.comparing(
                (GameState game) -> game.getSavedAt(),
                Comparator.nullsLast(Comparator.reverseOrder())));
        return games;
    }

    @Override
    public void deleteGame(UUID id) {
        Map<UUID, GameState> games = readGameStore();
        games.remove(id);
        writeObject(gameSavePath, games);
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

    private Map<UUID, GameState> readGameStore() {
        if (Files.notExists(gameSavePath)) {
            return new HashMap<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(gameSavePath))) {
            Object value = input.readObject();
            if (value instanceof Map<?, ?> map) {
                Map<UUID, GameState> games = new HashMap<>();
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (entry.getKey() instanceof UUID id && entry.getValue() instanceof GameState game) {
                        games.put(id, game);
                    }
                }
                return games;
            }

            // Older versions stored exactly one GameState. Keep it as the first save slot.
            if (value instanceof GameState game) {
                ensureSaveMetadata(game);
                Map<UUID, GameState> games = new HashMap<>();
                games.put(game.getId(), game);
                return games;
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.err.println("Could not load saved games: " + exception.getMessage());
        }

        return new HashMap<>();
    }

    private void ensureSaveMetadata(GameState game) {
        if (game.getId() == null) {
            game.setId(UUID.randomUUID());
        }
        if (game.getSaveName() == null || game.getSaveName().isBlank()) {
            String petName = game.getPet() == null ? "Pet" : game.getPet().getName();
            game.setSaveName((petName == null || petName.isBlank() ? "Pet" : petName) + "'s save");
        }
        if (game.getSavedAt() == null) {
            game.setSavedAt(java.time.LocalDateTime.now());
        }
    }
}
