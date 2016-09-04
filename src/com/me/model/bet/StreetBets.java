package com.me.model.bet;

import com.me.model.Bet;
import com.me.model.BetType;
import com.me.model.BetPayoff;

import java.util.Arrays;
import java.util.List;

public enum StreetBets implements Bets {
    STREET1(1, 2, 3),
    STREET2(4, 5, 6),
    STREET3(7, 8, 9),
    STREET4(10, 11, 12),
    STREET5(13, 14, 15),
    STREET6(16, 17, 18),
    STREET7(19, 20, 21),
    STREET8(22, 23, 24),
    STREET9(25, 26, 27),
    STREET10(28, 29, 30),
    STREET11(31, 32, 33),
    STREET12(34, 35, 36);

    private Bet bet;
    private List<Integer> numbers;

    StreetBets(int n1, int n2, int n3) {
        numbers = Arrays.asList(n1, n2, n3);
        bet = new Bet(this, BetPayoff.ELEVEN_TO_ONE);
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
