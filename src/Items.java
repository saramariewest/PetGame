public enum Items {
    CEREAL("Cereal", Type.FOOD, 10, 10),
    SUSHI("Sushi", Type.FOOD, 15, 15),
    CAKE("Cake", Type.FOOD, 20, 25),
    WATER("Water", Type.DRINK, 5, 10),
    ENERGY_DRINK("Energy Drink", Type.DRINK, 25, 30),
    JUICE("Juice", Type.DRINK, 12, 15),
    BALL("Ball", Type.TOY, 30, 10),
    SKATEBOARD("Skateboard", Type.TOY, 50, 20),
    CONSOLE("Console", Type.TOY, 100, 35);

    public final String displayName;
    public final Type type;
    public final int price;
    // Points decide how much the item improves the pet value.
    public final int points;

    Items(String displayName, Type type, int price, int points) {
        this.displayName = displayName;
        this.type = type;
        this.price = price;
        this.points = points;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
