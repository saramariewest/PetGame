package persistence;

import java.io.Serializable;

import model.Pet;
import model.Player;

public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;

    public Player player;
    public Pet pet;

    public GameState(Player player, Pet pet) {
        this.player = player;
        this.pet = pet;
    }
}
