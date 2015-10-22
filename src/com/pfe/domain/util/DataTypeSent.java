package com.pfe.domain.util;

public enum DataTypeSent {
	EMAIL(0),
    JSON(1),
    SMS(2);

    private final int value;

    private DataTypeSent(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}