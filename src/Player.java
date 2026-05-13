import java.util.HashMap;
import java.util.Map;

public class Player {
  private int coins = 0;
  private Map<Items, Integer> mapping = new HashMap<>();

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
}
