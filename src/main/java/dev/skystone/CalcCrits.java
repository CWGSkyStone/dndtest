package dev.skystone;

import java.util.Random;

public class CalcCrits {

    private final Random random = new Random();

    public String evaluateCrit(int roll, String critSuccessInput, String critFailInput) {

        int critSuccess = Integer.parseInt(critSuccessInput);
        int critFail = Integer.parseInt(critFailInput);

        StringBuilder out = new StringBuilder();
        out.append(" || Roll: ").append(roll);

        if (roll <= critFail) {
            out.append(" | CRITICAL FAILURE");
            out.append(" | ").append(getEvent(false));
        }
        else if (roll >= critSuccess) {
            out.append(" | CRITICAL SUCCESS");
            out.append(" | ").append(getEvent(true));
        }

        return out.toString();
    }

    private int rollD100() {
        return random.nextInt(100) + 1;
    }

    private String getEvent(boolean success) {

        D100Table table = D100Loader.getTable();
        int roll = rollD100();

        var list = success ? table.success : table.failure;

        for (D100Event e : list) {
            if (roll >= e.min && roll <= e.max) {
                return "[" + roll + "/100] " + e.text;
            }
        }

        return "No event found";
    }
}