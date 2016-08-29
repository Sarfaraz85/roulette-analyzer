package com.me.model.bet;

import com.me.model.Bet;
import com.me.model.BetType;
import com.me.model.BetPayoff;

import java.util.Arrays;
import java.util.List;

public enum CornerBets implements Bets {
    TOP1(3, 6, 2, 5),
    TOP2(6, 9, 5, 8),
    TOP3(9, 12, 8, 11),
    TOP4(12, 15, 11, 14),
    TOP5(15, 18, 14, 17),
    TOP6(18, 21, 17, 20),
    TOP7(21, 24, 20, 23),
    TOP8(24, 27, 23, 26),
    TOP9(27, 30, 26, 29),
    TOP10(30, 33, 29, 32),
    TOP11(33, 36, 32, 35),

    BOTTOM1(2, 5, 1, 4),
    BOTTOM2(5, 8, 4, 7),
    BOTTOM3(8, 11, 7, 10),
    BOTTOM4(11, 14, 10, 13),
    BOTTOM5(14, 17, 13, 16),
    BOTTOM6(17, 20, 16, 19),
    BOTTOM7(20, 23, 19, 22),
    BOTTOM8(23, 26, 22, 25),
    BOTTOM9(26, 29, 25, 28),
    BOTTOM10(29, 32, 28, 31),
    BOTTOM11(32, 35, 31, 34);

    private Bet bet;
    private List<Integer> numbers;

    CornerBets(int n1, int n2, int n3, int n4) {
        numbers = Arrays.asList(n1, n2, n3, n4);
        bet = new Bet(this, BetPayoff.EIGHT_TO_ONE);
    }

    @Override
    public Bet bet() {
        return bet;
    }

    @Override
    public List<Integer> numbers() {
        return numbers;
    }
}
