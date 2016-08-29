package com.me;

import com.me.model.Bet;
import com.me.model.UserBet;
import com.me.model.bet.CornerBets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.me.model.BetType.*;
import static com.me.model.BetPayoff.*;
import static java.util.Collections.singletonList;

public class BetProcessor {
//    public int unitBet = 5;
//    static int bankRoll = 200;
//    public static final Bet red = new Bet(RED, EVEN_MONEY, Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 21, 23, 25, 27, 28, 30, 32, 34, 36));
//    public static final Bet black = new Bet(BLACK, EVEN_MONEY, Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 19, 20, 22, 24, 26, 29, 31, 33, 35));

    public List<Bet> allBets = new ArrayList<>();

//    public List<UserBet> userBets = Arrays.asList(
//            new UserBet(RED, unitBet)
//            , new UserBet(LOW, unitBet)
////            , new UserBet(DOZEN1, unitBet)
//            , new UserBet(ODD, unitBet));

    public BetProcessor() {
        for (CornerBets corner : CornerBets.values())
            allBets.add(corner.bet());
//        allBets.add(black);
//        allBets.add(new Bet(LOW, EVEN_MONEY, numberSet(n -> n <= 18)));
//        allBets.add(new Bet(HIGH, EVEN_MONEY, numberSet(n -> n > 18)));
//        allBets.add(new Bet(EVEN, EVEN_MONEY, numberSet(n -> n % 2 == 0)));
//        allBets.add(new Bet(ODD, EVEN_MONEY, numberSet(n -> n % 2 != 0)));
//
//        allBets.add(new Bet(UPPER_COLUMN, TWO_TO_ONE, numberSet(n -> n % 3 == 0)));
//        allBets.add(new Bet(MID_COLUMN, TWO_TO_ONE, numberSet(n -> n % 3 == 2)));
//        allBets.add(new Bet(LOWER_COLUMN, TWO_TO_ONE, numberSet(n -> n % 3 == 1)));
//        allBets.add(new Bet(DOZEN1, TWO_TO_ONE, numberSet(n -> n <= 12)));
//        allBets.add(new Bet(DOZEN2, TWO_TO_ONE, numberSet(n -> n > 12 && n <= 24)));
//        allBets.add(new Bet(DOZEN3, TWO_TO_ONE, numberSet(n -> n > 24)));

//        IntStream.rangeClosed(0,36).forEach(n->allBets.add(new Bet(STRAIGHT_UP, THIRTYFIVE_TO_ONE, singletonList(n))));
//        allBets.add(new BetProcessor(SPLIT,SEVENTEEN_TO_ONE,Arrays.asList()))

    }


    public long userWinAmount(int hit, List<UserBet> userBets) {
        return allBets.stream()
                .filter(bet -> bet.getBet().numbers().contains(hit))
//                .peek(System.out::println)
                .map(bet -> userBets.stream()
                                .filter(ub -> ub.bet.equals(bet))
                                .peek(System.out::println)
                                .map(ub -> ub.wager * bet.getPayoff().value())
//                        .peek(System.out::println)
                                .findFirst()
                                .orElse(0)
                )
                .mapToInt(i -> i)
                .sum();
    }


    private List<Integer> numberSet(Predicate<Integer> predicate) {
        return IntStream.rangeClosed(1, 36).boxed().filter(predicate).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        BetProcessor bets = new BetProcessor();
////        System.out.println(bets.userWinAmount(2, bets.userBets));
//
//
//        System.out.println(
//                IntStream.range(0, 37).boxed()
//                        .peek(s -> System.out.println("*******\ntotal amount wagered = " + bets.userBets.stream().mapToInt(b -> b.wager).sum()))
//                        .peek(s -> bankRoll -= bets.userBets.stream().mapToInt(b -> b.wager).sum())
//                        .peek(s -> System.out.println("current bankroll = " + bankRoll))
//                        .peek(s -> System.out.println("number hit = " + s))
//                        .map(n -> bets.userWinAmount(n, bets.userBets))
//                        .peek(s -> System.out.println("win amount = " + s))
//                        .map(n -> bankRoll += n)
//                        .peek(s -> System.out.println("new bankroll = " + s))
//                        .collect(Collectors.toList())
//        );
//    }
}
