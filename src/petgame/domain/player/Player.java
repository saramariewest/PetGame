package petgame.domain.player;

import java.io.Serializable;
import java.util.*;

import petgame.domain.item.Item;
import petgame.domain.item.ItemType;

// Stores player progress, coins, and inventory.
public class Player implements Serializable {

  private static final long serialVersionUID = 1L;

  private int coins = 100;
  // Each item maps to the amount owned.
  private Map<Item, Integer> inventory = new HashMap<>();
  private long saveTime;

  public long getSaveTime() {
    return saveTime;
  }
  
  public void setSaveTime(long saveTime) {
    this.saveTime = saveTime;
  }

  public int getCoins() {
    return coins;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void passTime(long passedTime) {
    coins += 10 * passedTime / 10000;
  }

  public Map<Item, Integer> getInventory() {
    return inventory;
  }

  public void setInventory(Map<Item, Integer> inventory) {
    this.inventory = inventory;
  }

  public boolean hasItem(ItemType type) {
    for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
      if (entry.getKey().type == type && entry.getValue() > 0) {
        return true;
      }
    }

    return false;
  }

  public boolean useItem(Item item) {
    int amount = inventory.getOrDefault(item, 0);

    if (amount <= 0) {
      return false;
    }

    inventory.put(item, amount - 1);

    if (inventory.get(item) <= 0) {
      inventory.remove(item);
    }

    return true;
  }
}


