
public class Pet {

    private int hunger = 100;   // 0 = starving, 100 = full
    private int thirst = 100;   // 0 = dehydrated, 100 = quenched
    private int mood = 100;     // 0 = sad, 100 = happy
    private int energy = 100;   //  0 = exhausted, 100 = energetic

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
        hunger = Math.min(100, hunger + 10); // Increase hunger level by 10, max 100
    }

    public void drink() {
        thirst = Math.min(100, thirst + 10); // Increase thirst level by 10, max 100
    }

    public void play() {
        mood = Math.min(100, mood + 10); // Increase mood level by 10, max 100
        energy = Math.max(0, energy - 10); // Decrease energy level by 10, min 0
    }

    public void sleep() {
        energy = Math.min(100, energy + 20); // Increase energy level by 20, max 100
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
