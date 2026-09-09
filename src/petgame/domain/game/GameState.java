package petgame.domain.game;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import petgame.domain.pet.Pet;
import petgame.domain.player.Player;

public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String saveName;
    private LocalDateTime savedAt;
    private Player player;
    private Pet pet;

    public GameState(String saveName, Player player, Pet pet) {
        this.id = UUID.randomUUID();
        this.saveName = saveName;
        this.savedAt = LocalDateTime.now();
        this.player = player;
        this.pet = pet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
