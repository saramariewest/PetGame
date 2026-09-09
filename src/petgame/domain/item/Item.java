package petgame.domain.item;

public enum Item {
    CEREAL("Cereal", ItemType.FOOD, 10, 10),
    SUSHI("Sushi", ItemType.FOOD, 15, 15),
    CAKE("Cake", ItemType.FOOD, 20, 25),
    WATER("Water", ItemType.DRINK, 5, 10),
    ENERGY("Energy", ItemType.DRINK, 25, 30),
    JUICE("Juice", ItemType.DRINK, 12, 15),
    BALL("Ball", ItemType.TOY, 30, 10),
    SKATEBOARD("Skateboard", ItemType.TOY, 50, 20),
    CONSOLE("Console", ItemType.TOY, 100, 35);

    public final String displayName;
    public final ItemType type;
    public final int price;
    // More expensive items usually restore more points.
    public final int points;

    Item(String displayName, ItemType type, int price, int points) {
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
