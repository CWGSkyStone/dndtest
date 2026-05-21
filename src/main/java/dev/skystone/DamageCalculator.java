package dev.skystone;

import java.util.Random;

public class DamageCalculator {
    private static final Random random = new Random();

    public static int rollDamage(Weapon weapon, boolean critical){
        int diceToRoll = weapon.getDiceCount();
        if(critical){{
            diceToRoll *= 2;
        }}
        int total = 0;
        for(int i = 0; i < diceToRoll; i++){
            total += random.nextInt(weapon.getDiceSides()) + 1;
        }
        return total + weapon.getModifier();
    }
}
