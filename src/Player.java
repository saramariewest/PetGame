import java.util.HashMap;
import java.util.Map;

public class Player {
  private int coins = 0;
  private Map<Items, String> mapping = new HashMap<>();

  public int getCoins() {
    return coins;
  }

  public void passTime() {
    coins += 5;
  }

  public Map<Items, String> getMapping() {
    return mapping;
  }

  public void setMapping(Map<Items, String> mapping) {
    this.mapping = mapping;
  }
}
