package com.me;

import com.me.model.UserBet;
import com.me.model.bet.CornerBets;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.me.model.BetType.BLACK;
import static com.me.model.BetType.MID_COLUMN;

public class Main {
    private static int bankRoll = 200;
    private static int minBet = 1;
    private static BetProcessor betProcessor;


    private static List<UserBet> userBets = Arrays.asList(
            new UserBet(CornerBets.BOTTOM1.bet(), minBet),
            new UserBet(CornerBets.BOTTOM2.bet(), minBet),
            new UserBet(CornerBets.BOTTOM3.bet(), minBet)
//            , new UserBet(CORNER,)
//            , new UserBet(STRAIGHT_UP, minBet)
//            , new UserBet(DOZEN1, minBet)
//            , new UserBet(ODD, minBet)
    );


    public static void main(String[] args) throws IOException {


        betProcessor = new BetProcessor();
        List<Integer> bankrollProgression = Files.lines(Paths.get("/Users/nabyusuf/Downloads/10K.Real.Double0.txt"))
//                .peek(System.out::println)
                .filter(s -> s.trim().matches("^[0-9][0-9]*$"))
                .map(Integer::parseInt)
//                .filter(n -> bankRoll >= -1000)
                .peek(n -> System.out.println("*******\namount wagered for this turn = " + userBets.stream().mapToInt(b -> b.wager).sum()))
                .peek(n -> bankRoll -= userBets.stream().mapToInt(b -> b.wager).sum())
                .peek(n -> System.out.println("current bankroll = " + bankRoll))
                .peek(n -> System.out.println("number hit = " + n))
                .peek(n -> bankRoll += betProcessor.userWinAmount(n, userBets))
                .peek(n -> System.out.println("new bankroll = " + bankRoll))
                .peek(Main::updateUserBets)
                .map(n -> bankRoll)
                .collect(Collectors.toList());

        System.out.println(bankrollProgression);
        System.out.println(bankrollProgression.size());


        Chart chart = new Chart(
                "Roulette strategy analyzer",
                "Bankroll progression",
                bankrollProgression);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);


    }

    public static void updateUserBets(Integer lastWinningNumber) {
        userBets = new ArrayList<>();
//        if (BetProcessor.red.numbers.contains(lastWinningNumber))
//            userBets.add(new UserBet(RED, minBet));
//        else
//            userBets.add(new UserBet(BLACK, minBet));

//        if (lastWinningNumber > 18)
//            userBets.add(new UserBet(HIGH, minBet));
//        else
//            userBets.add(new UserBet(LOW, minBet));

//        if (lastWinningNumber % 2 == 0)
//            userBets.add(new UserBet(EVEN, minBet));
//        else
//            userBets.add(new UserBet(ODD, minBet));


//        userBets = Arrays.asList(
//                new UserBet(BLACK, minBet)
//                , new UserBet(MID_COLUMN, minBet));
    }
}
