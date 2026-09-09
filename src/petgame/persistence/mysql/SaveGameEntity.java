package petgame.persistence.mysql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "save_games")
public class SaveGameEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "save_name", nullable = false, length = 100)
    private String saveName;

    @Column(name = "saved_at", nullable = false)
    private LocalDateTime savedAt;

    @Column(name = "pet_name", nullable = false, length = 100)
    private String petName;
    private int hunger;
    private int thirst;
    private int mood;
    private int energy;
    private int level;
    private int experience;
    private boolean alive;

    @Column(name = "critical_ticks")
    private int criticalTicks;
    @Column(name = "init_timestamp")
    private long initTimestamp;
    @Column(name = "death_timestamp")
    private long deathTimestamp;
    @Column(name = "pet_save_time")
    private long petSaveTime;
    @Column(name = "highscore_recorded")
    private boolean highscoreRecorded;
    private int coins;
    @Column(name = "player_save_time")
    private long playerSaveTime;

    @OneToMany(mappedBy = "saveGame", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<InventoryEntryEntity> inventoryEntries = new ArrayList<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSaveName() { return saveName; }
    public void setSaveName(String saveName) { this.saveName = saveName; }
    public LocalDateTime getSavedAt() { return savedAt; }
    public void setSavedAt(LocalDateTime savedAt) { this.savedAt = savedAt; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public int getHunger() { return hunger; }
    public void setHunger(int hunger) { this.hunger = hunger; }
    public int getThirst() { return thirst; }
    public void setThirst(int thirst) { this.thirst = thirst; }
    public int getMood() { return mood; }
    public void setMood(int mood) { this.mood = mood; }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    public int getCriticalTicks() { return criticalTicks; }
    public void setCriticalTicks(int criticalTicks) { this.criticalTicks = criticalTicks; }
    public long getInitTimestamp() { return initTimestamp; }
    public void setInitTimestamp(long initTimestamp) { this.initTimestamp = initTimestamp; }
    public long getDeathTimestamp() { return deathTimestamp; }
    public void setDeathTimestamp(long deathTimestamp) { this.deathTimestamp = deathTimestamp; }
    public long getPetSaveTime() { return petSaveTime; }
    public void setPetSaveTime(long petSaveTime) { this.petSaveTime = petSaveTime; }
    public boolean isHighscoreRecorded() { return highscoreRecorded; }
    public void setHighscoreRecorded(boolean highscoreRecorded) { this.highscoreRecorded = highscoreRecorded; }
    public int getCoins() { return coins; }
    public void setCoins(int coins) { this.coins = coins; }
    public long getPlayerSaveTime() { return playerSaveTime; }
    public void setPlayerSaveTime(long playerSaveTime) { this.playerSaveTime = playerSaveTime; }
    public List<InventoryEntryEntity> getInventoryEntries() { return inventoryEntries; }

    public void replaceInventoryEntries(List<InventoryEntryEntity> entries) {
        inventoryEntries.clear();
        for (InventoryEntryEntity entry : entries) {
            entry.setSaveGame(this);
            inventoryEntries.add(entry);
        }
    }
}
