package dev.skystone;

public class D100Table {

    private CritTable melee;
    private CritTable ranged;

    public CritTable getMelee() {
        return melee;
    }

    public CritTable getRanged() {
        return ranged;
    }
}