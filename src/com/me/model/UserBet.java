package com.me.model;


public class UserBet {
    public Bet bet;
    public int wager;

    public UserBet(Bet bet, int wager) {
        this.bet = bet;
        this.wager = wager;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "bet=" + bet +
                ", wager=" + wager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBet userBet = (UserBet) o;

        if (wager != userBet.wager) return false;
        return bet != null ? bet.equals(userBet.bet) : userBet.bet == null;

    }

    @Override
    public int hashCode() {
        int result = bet != null ? bet.hashCode() : 0;
        result = 31 * result + wager;
        return result;
    }
}
