package model;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class HighscoreEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    private final String name;
    private final int level;
    private final long survivalTimeMillis;
    private final long score;
    private final long createdAt;

    public HighscoreEntry(String name, int level, long survivalTimeMillis) {
        this.name = name;
        this.level = level;
        this.survivalTimeMillis = survivalTimeMillis;
        this.score = calculateScore(level, survivalTimeMillis);
        this.createdAt = Instant.now().toEpochMilli();
    }

    public static long calculateScore(int level, long survivalTimeMillis) {
        long survivalSeconds = Math.max(0L, survivalTimeMillis / 1000L);
        return (long) level * 1000L + survivalSeconds;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public long getSurvivalTimeMillis() {
        return survivalTimeMillis;
    }

    public long getScore() {
        return score;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String formatDuration() {
        long totalSeconds = Math.max(0L, survivalTimeMillis / 1000L);
        long hours = totalSeconds / 3600L;
        long minutes = (totalSeconds % 3600L) / 60L;
        long seconds = totalSeconds % 60L;

        if (hours > 0) {
            return String.format("%dh %02dm %02ds", hours, minutes, seconds);
        }

        return String.format("%dm %02ds", minutes, seconds);
    }

    public String formatCreatedAt() {
        return TIME_FORMATTER.format(Instant.ofEpochMilli(createdAt));
    }
}
