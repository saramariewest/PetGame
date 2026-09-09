package petgame.persistence.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petgame.domain.game.GameState;
import petgame.domain.highscore.HighscoreEntry;
import petgame.domain.item.Item;
import petgame.domain.pet.Pet;
import petgame.domain.player.Player;
import petgame.persistence.GameDataStore;

@Service
public class MySqlGameDataStore implements GameDataStore {

    private final SaveGameRepository saveGameRepository;
    private final HighscoreRepository highscoreRepository;

    public MySqlGameDataStore(
            SaveGameRepository saveGameRepository,
            HighscoreRepository highscoreRepository) {
        this.saveGameRepository = saveGameRepository;
        this.highscoreRepository = highscoreRepository;
    }

    @Override
    @Transactional
    public void saveGame(GameState gameState) {
        if (gameState.getId() == null) {
            gameState.setId(UUID.randomUUID());
        }

        SaveGameEntity entity = saveGameRepository.findById(gameState.getId().toString())
                .orElseGet(SaveGameEntity::new);
        copyToEntity(gameState, entity);
        saveGameRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GameState> loadGame(UUID id) {
        return saveGameRepository.findById(id.toString()).map(this::toGameState);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameState> loadAllGames() {
        return saveGameRepository.findAllByOrderBySavedAtDesc().stream()
                .map(this::toGameState)
                .toList();
    }

    @Override
    @Transactional
    public void deleteGame(UUID id) {
        saveGameRepository.deleteById(id.toString());
    }

    @Override
    @Transactional
    public void addHighscore(HighscoreEntry entry) {
        HighscoreEntity entity = new HighscoreEntity();
        entity.setPlayerName(entry.getName());
        entity.setLevel(entry.getLevel());
        entity.setSurvivalTimeMillis(entry.getSurvivalTimeMillis());
        entity.setScore(entry.getScore());
        entity.setCreatedAt(entry.getCreatedAt());
        highscoreRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HighscoreEntry> loadHighscores() {
        return highscoreRepository.findTop10ByOrderByScoreDescLevelDescSurvivalTimeMillisDesc().stream()
                .map(entity -> HighscoreEntry.restore(
                        entity.getPlayerName(),
                        entity.getLevel(),
                        entity.getSurvivalTimeMillis(),
                        entity.getScore(),
                        entity.getCreatedAt()))
                .toList();
    }

    private void copyToEntity(GameState gameState, SaveGameEntity entity) {
        Pet pet = gameState.getPet();
        Player player = gameState.getPlayer();

        entity.setId(gameState.getId().toString());
        entity.setSaveName(gameState.getSaveName());
        entity.setSavedAt(gameState.getSavedAt());
        entity.setPetName(pet.getName());
        entity.setHunger(pet.getHunger());
        entity.setThirst(pet.getThirst());
        entity.setMood(pet.getMood());
        entity.setEnergy(pet.getEnergy());
        entity.setLevel(pet.getLevel());
        entity.setExperience(pet.getExperience());
        entity.setAlive(pet.isAlive());
        entity.setCriticalTicks(pet.getCriticalTicks());
        entity.setInitTimestamp(pet.getInitTimestamp());
        entity.setDeathTimestamp(pet.getDeathTimestamp());
        entity.setPetSaveTime(pet.getSaveTime());
        entity.setHighscoreRecorded(pet.isHighscoreRecorded());
        entity.setCoins(player.getCoins());
        entity.setPlayerSaveTime(player.getSaveTime());

        List<InventoryEntryEntity> inventoryEntries = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : player.getInventory().entrySet()) {
            if (entry.getValue() > 0) {
                inventoryEntries.add(new InventoryEntryEntity(entry.getKey(), entry.getValue()));
            }
        }
        entity.replaceInventoryEntries(inventoryEntries);
    }

    private GameState toGameState(SaveGameEntity entity) {
        Pet pet = Pet.restore(
                entity.getPetName(),
                entity.getHunger(),
                entity.getThirst(),
                entity.getMood(),
                entity.getEnergy(),
                entity.getLevel(),
                entity.getExperience(),
                entity.isAlive(),
                entity.getCriticalTicks(),
                entity.getInitTimestamp(),
                entity.getDeathTimestamp(),
                entity.getPetSaveTime(),
                entity.isHighscoreRecorded());

        Player player = new Player();
        player.setCoins(entity.getCoins());
        player.setSaveTime(entity.getPlayerSaveTime());
        for (InventoryEntryEntity inventoryEntry : entity.getInventoryEntries()) {
            player.getInventory().put(inventoryEntry.getItem(), inventoryEntry.getQuantity());
        }

        GameState gameState = new GameState(entity.getSaveName(), player, pet);
        gameState.setId(UUID.fromString(entity.getId()));
        gameState.setSavedAt(entity.getSavedAt());
        return gameState;
    }
}
