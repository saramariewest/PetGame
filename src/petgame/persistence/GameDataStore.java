package petgame.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;

public interface GameDataStore {

    void saveGame(GameState gameState);

    Optional<GameState> loadGame(UUID id);

    List<GameState> loadAllGames();

    void deleteGame(UUID id);

    void addHighscore(HighscoreEntry entry);

    List<HighscoreEntry> loadHighscores();
}
