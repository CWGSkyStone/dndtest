package dev.skystone;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class D100Loader {

    private static D100Table table;

    public static void load() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = D100Loader.class.getResourceAsStream("/dev/skystone/d100_events.json");

            if (is == null) {
                throw new RuntimeException("d100_events.json not found in resources!");
            }

            table = mapper.readValue(is, D100Table.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load d100 events", e);
        }
    }

    public static D100Table getTable() {
        return table;
    }
}