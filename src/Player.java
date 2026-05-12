public class Player {
  private int coins = 0;

  public int getCoins() {
    return coins;
  }

  public void passTime() {
    coins += 10;
  }
}
