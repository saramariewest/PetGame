import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Player contains the data that belongs to the person playing the game.
public class Player implements Serializable {

  private static final long serialVersionUID = 1L;

  private int coins = 100;
  // The key is the item, the value is the amount owned by the player.
  private Map<Items, Integer> inventory = new HashMap<>();
  private transient PetInventory inventoryWindow;

  public int getCoins() {
    return coins;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void passTime() {
    coins += 10;
  }

  public Map<Items, Integer> getInventory() {
    return inventory;
  }

  public void setInventory(Map<Items, Integer> inventory) {
    this.inventory = inventory;
  }

  public void setInventoryWindow(PetInventory inv) {
    this.inventoryWindow = inv;
  }

  public PetInventory getInventoryWindow() {
    return inventoryWindow;
  }

  public boolean hasItem(Type type) {
    // Actions only need to know if any matching item exists.
    for (Map.Entry<Items, Integer> entry : inventory.entrySet()) {
      if (entry.getKey().type == type && entry.getValue() > 0) {
        return true;
      }
    }

    return false;
  }

  public boolean useItem(Items item) {
    // Items are stored as amounts, so using one item lowers the amount by one.
    int amount = inventory.getOrDefault(item, 0);

    if (amount <= 0) {
      return false;
    }

    inventory.put(item, amount - 1);

    if (inventory.get(item) <= 0) {
      inventory.remove(item);
    }

    if (inventoryWindow != null) {
      inventoryWindow.updateLabel(item);
    }

    return true;
  }
}
