package dev.skystone;

import java.util.List;
import java.util.Random;

public class CalcCrits {

    private final Random random = new Random();

    public String evaluateCrit(int roll,
                               String critSuccessInput,
                               String critFailInput,
                               String attackType) {

        int critSuccess = parseOrDefault(critSuccessInput, 20);
        int critFail = parseOrDefault(critFailInput, 1);

        // Load table
        D100Table table = D100Loader.getTable();

        if (table == null) {
            return "ERROR: D100 table not loaded";
        }

        // 🎯 Critical Success
        if (roll >= critSuccess) {
            return handleCrit("SUCCESS", roll, attackType, table);
        }

        // 💀 Critical Failure
        if (roll <= critFail) {
            return handleCrit("FAILURE", roll, attackType, table);
        }

        // Normal roll
        return " | Roll: " + roll;
    }

    private String handleCrit(String type,
                              int roll,
                              String attackType,
                              D100Table table) {

        int d100 = random.nextInt(100) + 1;

        List<D100Event> events;

        if (attackType.equalsIgnoreCase("MELEE")) {
            events = type.equals("SUCCESS")
                    ? table.getMelee().getSuccess()
                    : table.getMelee().getFailure();
        } else {
            events = type.equals("SUCCESS")
                    ? table.getRanged().getSuccess()
                    : table.getRanged().getFailure();
        }

        String eventText = findEvent(d100, events);

        return (type.equals("SUCCESS") ? " | CRITICAL SUCCESS! | " : " | CRITICAL FAIL! | ")
                + "(Roll: " + roll + ", d100: " + d100 + ") → "
                + eventText;
    }

    private String findEvent(int roll, List<D100Event> events) {
        if (events == null || events.isEmpty()) {
            return "No event found";
        }

        for (D100Event e : events) {
            if (roll >= e.getMin() && roll <= e.getMax()) {
                return e.getText();
            }
        }

        return "No matching event";
    }

    private int parseOrDefault(String value, int def) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return def;
        }
    }
}