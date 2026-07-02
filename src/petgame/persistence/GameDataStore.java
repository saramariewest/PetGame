package petgame.persistence;

import java.util.List;
import java.util.Optional;

import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;

public interface GameDataStore {

    void saveGame(GameState gameState);

    Optional<GameState> loadGame();

    void addHighscore(HighscoreEntry entry);

    List<HighscoreEntry> loadHighscores();
}
