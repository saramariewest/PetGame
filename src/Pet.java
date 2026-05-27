public class Pet {

    private int hunger = 100; // 0 = starving, 100 = full
    private int thirst = 100; // 0 = dehydrated, 100 = quenched
    private int mood = 100; // 0 = sad, 100 = happy
    private int energy = 100; // 0 = exhausted, 100 = energetic

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

    public void feed() {
        feed(10);
    }

    public void feed(int amount) {
        hunger = Math.min(100, hunger + amount);
    }

    public void drink() {
        drink(10);
    }

    public void drink(int amount) {
        thirst = Math.min(100, thirst + amount);
    }

    public void play() {
        play(10);
    }

    public void play(int moodBoost) {
        mood = Math.min(100, mood + moodBoost);
        energy = Math.max(0, energy - 10);
    }

    public void sleep() {
        energy = Math.min(100, energy + 20);
    }

    public void passTime() {
        hunger = Math.max(0, hunger - 5);
        thirst = Math.max(0, thirst - 5);
        mood = Math.max(0, mood - 2);

        if (hunger == 0 || thirst == 0) {
            mood = Math.max(0, mood - 25);
        }
    }
}
