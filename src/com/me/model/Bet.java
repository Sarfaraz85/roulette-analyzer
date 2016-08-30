package com.me.model;

import com.me.model.bet.Bets;

public class Bet {
    private Bets bet;
    private BetPayoff payoff;

    public Bet(Bets bet, BetPayoff payoff) {
        this.bet = bet;
        this.payoff = payoff;
    }

    public Bets getBet() {
        return bet;
    }

    public BetPayoff getPayoff() {
        return payoff;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "bet=" + bet +
                ", payoff=" + payoff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet1 = (Bet) o;

        if (bet != null ? !bet.equals(bet1.bet) : bet1.bet != null) return false;
        return payoff == bet1.payoff;

    }

    @Override
    public int hashCode() {
        int result = bet != null ? bet.hashCode() : 0;
        result = 31 * result + (payoff != null ? payoff.hashCode() : 0);
        return result;
    }
}
