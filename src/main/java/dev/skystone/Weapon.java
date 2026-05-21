package dev.skystone;

public class Weapon {
    private final String name;
    private final int diceCount;
    private final int diceSides;
    private final int modifier;

    public Weapon(String name, int diceCount, int diceSides, int modifier) {
        this.name = name;
        this.diceCount = diceCount;
        this.diceSides = diceSides;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int getDiceSides() {
        return diceSides;
    }

    public int getModifier() {
        return modifier;
    }

    @Override
    public String toString(){
        return name + " (" + diceCount + "d" + diceSides + " + " + modifier + ")";
    }

}
