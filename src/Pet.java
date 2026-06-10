import java.io.Serializable;
import java.time.Instant;

// Pet contains the values that change during the game.
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_VALUE = 100;
    private static final int CRITICAL_TICKS_UNTIL_DEATH = 3;

    private int hunger = 100; // 0 = starving, 100 = full
    private int thirst = 100; // 0 = dehydrated, 100 = quenched
    private int mood = 100; // 0 = sad, 100 = happy
    private int energy = 100; // 0 = exhausted, 100 = energetic
    private String name;
    private int level = 1;
    private int experience = 0;
    private boolean alive = true;
    private int criticalTicks = 0;
    private long initTimestamp;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
        this.initTimestamp = Instant.now().toEpochMilli();
    }

    public long getInitTimestamp() {
        return initTimestamp;
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

    public EvolutionStage getEvolutionStage() {
        if (level >= EvolutionStage.ADULT.requiredLevel) {
            return EvolutionStage.ADULT;
        }

        if (level >= EvolutionStage.TEEN.requiredLevel) {
            return EvolutionStage.TEEN;
        }

        return EvolutionStage.BABY;
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

        // Time passing makes the pet need attention again.
        var newHunger = 2 * passedTime / 10000;
        var newThirst = 3 * passedTime / 10000;
        var newMood = 1 * passedTime / 10000;
        var newEnergy = 1 * passedTime / 10000;

        hunger = Math.max(0, hunger - Math.toIntExact(newHunger));
        thirst = Math.max(0, thirst - Math.toIntExact(newThirst));
        mood = Math.max(0, mood - Math.toIntExact(newMood));
        energy = Math.max(0, energy - Math.toIntExact(newEnergy));

        if (hunger == 0 && thirst == 0) {
            criticalTicks++;
            mood = Math.max(0, mood - 10);

            if (criticalTicks >= CRITICAL_TICKS_UNTIL_DEATH) {
                alive = false;
            }
        } else {
            criticalTicks = 0;
        }
    }

}
