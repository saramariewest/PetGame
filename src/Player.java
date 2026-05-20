import java.util.HashMap;
import java.util.Map;

public class Player {
  private int coins = 100;
  private Map<Items, Integer> mapping = new HashMap<>();
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

  public Map<Items, Integer> getMapping() {
    return mapping;
  }

  public void setMapping(Map<Items, Integer> mapping) {
    this.mapping = mapping;
  }

  public void setInventoryWindow(PetInventory inv) {
    this.inventoryWindow = inv;
  }

  public PetInventory getInventoryWindow() {
    return inventoryWindow;
  }

}
