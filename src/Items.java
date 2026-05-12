public enum Items {
    CEREAL("Cereal", Type.FOOD, 10),
    SUSHI("Sushi", Type.FOOD, 15),
    CAKE("Cake", Type.FOOD, 20),
    WATER("Water", Type.DRINK, 5),
    ENERGY_DRINK("Energy Drink", Type.DRINK, 25),
    JUICE("Juice", Type.DRINK, 12),
    BALL("Ball", Type.TOY, 30),
    SKATEBOARD("Skateboard", Type.TOY, 50),
    CONSOLE("Console", Type.TOY, 100);

    public final String displayName;
    public final Type type;
    public final int price;

    Items(String displayName, Type type, int price) {
        this.displayName = displayName;
        this.type = type;
        this.price = price;
    }
}
