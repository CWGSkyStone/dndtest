package dev.skystone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CalcRolls {

    private final Random random = new Random();

    public int rollD20(boolean advantage, boolean disadvantage, boolean inspiration) {

        List<Integer> rolls = new ArrayList<>();

        rolls.add(roll());

        boolean hasAdv = advantage && !disadvantage;
        boolean hasDis = disadvantage && !advantage;

        if (hasAdv || hasDis || inspiration) {
            rolls.add(roll());
        }

        if (inspiration) {
            rolls.add(roll());
        }

        if (hasAdv) {
            return Collections.max(rolls);
        }

        if (hasDis) {
            return Collections.min(rolls);
        }

        return rolls.get(0);
    }

    private int roll() {
        return random.nextInt(20) + 1;
    }
}