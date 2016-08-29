package com.me.model;

/**
 * Created by nabyusuf on 8/24/16.
 */
public enum BetPayoff {
    EVEN_MONEY(2), TWO_TO_ONE(3), THIRTYFIVE_TO_ONE(36), SEVENTEEN_TO_ONE(18), ELEVEN_TO_ONE(12), EIGHT_TO_ONE(9), FIVE_TO_ONE(6);

    private int value;

    BetPayoff(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
