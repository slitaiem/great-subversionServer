package com.pfe.domain.util;

public enum Priority {
	LOW(0),
    MEDIUM(1),
    HIGH(2),
    VERY_IMPORTANT(3);

    private final int value;

    private Priority(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}