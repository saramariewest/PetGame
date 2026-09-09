package petgame.persistence.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "highscores")
public class HighscoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "player_name", nullable = false, length = 100)
    private String playerName;
    private int level;
    @Column(name = "survival_time_millis")
    private long survivalTimeMillis;
    private long score;
    @Column(name = "created_at")
    private long createdAt;

    public Long getId() { return id; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public long getSurvivalTimeMillis() { return survivalTimeMillis; }
    public void setSurvivalTimeMillis(long survivalTimeMillis) { this.survivalTimeMillis = survivalTimeMillis; }
    public long getScore() { return score; }
    public void setScore(long score) { this.score = score; }
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
