package com.borovoi.dmitrii.crocodileassistant;

/**
 * Created by dimas on 11/2/2015.
 */
public enum Level {
    LOW(0),
    MIDDLE(1),
    HIGH(2);

    private int code;

    Level(int code) {
        this.code = code;
    }

    public static Level findByCode(int code) {
        for (Level unit : values()) {
            if (unit.code == code)
                return unit;
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
