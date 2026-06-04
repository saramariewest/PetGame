public enum EvolutionStage {
    BABY("Baby", 1, "stage_one.png"),
    TEEN("Teen", 5, "stage_two.png"),
    ADULT("Adult", 10, "stage_three.png");

    public final String displayName;
    public final int requiredLevel;
    public final String spriteFile;

    EvolutionStage(String displayName, int requiredLevel, String spriteFile) {
        this.displayName = displayName;
        this.requiredLevel = requiredLevel;
        this.spriteFile = spriteFile;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
