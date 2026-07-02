package petgame.domain.pet;

import java.io.Serializable;
import java.time.Instant;

// Stores the pet state and core game rules.
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_VALUE = 100;
    private static final int CRITICAL_TICKS_UNTIL_DEATH = 3;

    private int hunger = 100; // 0 = starving, 100 = full.
    private int thirst = 100; // 0 = dehydrated, 100 = quenched.
    private int mood = 100; // 0 = sad, 100 = happy.
    private int energy = 100; // 0 = exhausted, 100 = energetic.
    private String name;
    private int level = 1;
    private int experience = 0;
    private boolean alive = true;
    private int criticalTicks = 0;
    private long initTimestamp;
    private long deathTimestamp;
    private long saveTime;
    private boolean highscoreRecorded;

    public Pet() {
        this.initTimestamp = Instant.now().toEpochMilli();
    }

    public Pet(String name) {
        this();
        this.name = name;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public long getInitTimestamp() {
        return initTimestamp;
    }

    public long getSurvivalTimeMillis() {
        if (initTimestamp == 0) {
            return 0;
        }

        if (deathTimestamp > 0) {
            return deathTimestamp - initTimestamp;
        }

        return Instant.now().toEpochMilli() - initTimestamp;
    }

    public boolean isHighscoreRecorded() {
        return highscoreRecorded;
    }

    public void setHighscoreRecorded(boolean highscoreRecorded) {
        this.highscoreRecorded = highscoreRecorded;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public int getMood() {
        return mood;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getExperienceForNextLevel() {
        return level * 25;
    }

    public boolean isAlive() {
        return alive;
    }

    public PetEvolutionStage getEvolutionStage() {
        if (level >= PetEvolutionStage.ADULT.requiredLevel) {
            return PetEvolutionStage.ADULT;
        }

        if (level >= PetEvolutionStage.TEEN.requiredLevel) {
            return PetEvolutionStage.TEEN;
        }

        return PetEvolutionStage.BABY;
    }

    public void feed() {
        feed(10);
    }

    public void feed(int amount) {
        if (!alive) {
            return;
        }

        hunger = Math.min(MAX_VALUE, hunger + amount);
    }

    public void drink() {
        drink(10);
    }

    public void drink(int amount) {
        if (!alive) {
            return;
        }

        thirst = Math.min(MAX_VALUE, thirst + amount);
    }

    public void play() {
        play(10);
    }

    public void play(int moodBoost) {
        if (!alive) {
            return;
        }

        mood = Math.min(MAX_VALUE, mood + moodBoost);
        energy = Math.max(0, energy - 10);
    }

    public void sleep() {
        if (!alive) {
            return;
        }

        energy = Math.min(MAX_VALUE, energy + 20);
    }

    public void addExperience(int amount) {
        if (!alive) {
            return;
        }

        experience += amount;

        while (experience >= getExperienceForNextLevel()) {
            experience -= getExperienceForNextLevel();
            level++;
        }
    }

    public void passTime(long passedTime) {
        if (!alive) {
            return;
        }

        // Use seconds so the decay curve stays easy to tune.
        double x = passedTime / 1000.0;

        double a = -0.0000122087;
        double b = 0.0860986;
        double c = 2.91862;

        double newHunger = (a * x * x + b * x + c) * 0.5;
        double newThirst = a * x * x + b * x + c;
        double newMood = a * x * x + b * x + c;
        double newEnergy = a * x * x + b * x + c;

        // Clamp decay before converting it to integer stat changes.
        hunger = Math.max(0, hunger - (int) Math.min(Integer.MAX_VALUE, Math.round(Math.max(0, newHunger))));
        thirst = Math.max(0, thirst - (int) Math.min(Integer.MAX_VALUE, Math.round(Math.max(0, newThirst))));
        mood = Math.max(0, mood - (int) Math.min(Integer.MAX_VALUE, Math.round(Math.max(0, newMood))));
        energy = Math.max(0, energy - (int) Math.min(Integer.MAX_VALUE, Math.round(Math.max(0, newEnergy))));

        if (hunger == 0 && thirst == 0) {
            criticalTicks++;
            mood = Math.max(0, mood - 10);

            if (criticalTicks >= CRITICAL_TICKS_UNTIL_DEATH) {
                alive = false;
                deathTimestamp = Instant.now().toEpochMilli();
            }
        } else {
            criticalTicks = 0;
        }
    }

}


