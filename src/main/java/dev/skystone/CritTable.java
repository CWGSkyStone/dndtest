package dev.skystone;

import java.util.List;

public class CritTable {

    private List<D100Event> success;
    private List<D100Event> failure;

    public List<D100Event> getSuccess() {
        return success;
    }

    public List<D100Event> getFailure() {
        return failure;
    }
}