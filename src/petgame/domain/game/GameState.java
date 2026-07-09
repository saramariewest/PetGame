package petgame.domain.game;

import java.io.Serializable;
import petgame.domain.pet.Pet;
import petgame.domain.player.Player;

public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Player player;
    private final Pet pet;

    public GameState(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }
}
