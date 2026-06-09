
public class GameTime {
  private long lastSavedAt;

  public GameTime(long lastSavedAt) {
    this.lastSavedAt = lastSavedAt;
  }

  public static GameTime now() {
    return new GameTime(System.currentTimeMillis());
  }

  public int getOfflineTicks(int tickMillis) {
    long elapsed = System.currentTimeMillis() - lastSavedAt;
    return (int) (elapsed / tickMillis);
  }

  public void reset() {
    lastSavedAt = System.currentTimeMillis();
  }

  public long getLastSavedAt() {
    return lastSavedAt;
  }
}
