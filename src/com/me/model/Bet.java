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
}
