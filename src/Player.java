import java.util.HashMap;
import java.util.Map;

public class Player {

  private int coins = 100;
  private Map<Items, Integer> inventory = new HashMap<>();
  private PetInventory inventoryWindow;

  public int getCoins() {
    return coins;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void passTime() {
    coins += 5;
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
    for (Map.Entry<Items, Integer> entry : inventory.entrySet()) {
      if (entry.getKey().type == type && entry.getValue() > 0) {
        return true;
      }
    }

    return false;
  }

  public boolean useItem(Items item) {
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
