package com.me;

import com.me.model.UserBet;
import com.me.model.bet.Bets;
import com.me.model.bet.CornerBets;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static int initialBankroll = 200;
    private static int runningBankroll = initialBankroll;
    private static int minBet = 2;
    private static int spinCount = 0;
    private static final int nonOccurrenceCountThreshold = 12;
    private static BetProcessor betProcessor;

    private static Map<Bets, Integer> cornerBetsLastOccurences = new HashMap<>();


    private static List<UserBet> userBets = Arrays.asList(
//            new UserBet(CornerBets.TOP1.bet(), minBet),
//            new UserBet(CornerBets.BOTTOM6.bet(), minBet),
//            new UserBet(CornerBets.TOP11.bet(), minBet)
//            , new UserBet(CORNER,)
//            , new UserBet(STRAIGHT_UP, minBet)
//            , new UserBet(DOZEN1, minBet)
//            , new UserBet(ODD, minBet)
    );


    public static void main(String[] args) throws IOException {

        for (CornerBets corner : CornerBets.values())
            cornerBetsLastOccurences.put(corner, 0);

        betProcessor = new BetProcessor();
        List<Integer> bankrollProgression =
                Files.lines(Paths.get("/Users/nabyusuf/Downloads/10K.Real.Double0.txt"))
                        .filter(s -> s.trim().matches("^[0-9][0-9]*$"))
                        .map(Integer::parseInt)
//                IntStream.range(0,36).boxed()
//                .peek(System.out::println)
                        .limit(1000)
//                .filter(n -> runningBankroll >= -100)
                        .peek(n -> System.out.println("*******\nspinCount: " + ++spinCount))
                        .peek(n -> System.out.println("amount wagered for this turn = " + userBets.stream().mapToInt(b -> b.wager).sum()))
//                        .peek(n -> userBets.forEach(b -> System.out.println(b.bet)))
                        .peek(n -> System.out.println(cornerBetsLastOccurences))
                        .peek(n -> runningBankroll -= userBets.stream().mapToInt(b -> b.wager).sum())
                        .peek(n -> System.out.println("current bankroll = " + runningBankroll))
                        .peek(n -> System.out.println("number hit = " + n))
                        .peek(n -> runningBankroll += betProcessor.userWinAmount(n, userBets))
                        .peek(n -> System.out.println("new bankroll = " + runningBankroll))
                        .peek(Main::updateCornerBetsLastOccurences)
                        .peek(Main::updateUserBets)
                        .map(n -> runningBankroll)
                        .collect(Collectors.toList());

        System.out.println(bankrollProgression);
        System.out.println(bankrollProgression.size());


        Chart chart = new Chart(
                "Roulette strategy analyzer",
                "Bankroll progression",
                bankrollProgression, initialBankroll);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);


    }

    private static void updateCornerBetsLastOccurences(Integer hit) {
//        cornerBetsLastOccurences.entrySet().stream().map(entry -> entry.getKey().numbers().contains(hit) ? cornerBetsLastOccurences.put(entry.getKey(), spinCount) : spinCount);
        for(Map.Entry<Bets,Integer> entry : cornerBetsLastOccurences.entrySet()){
            if (entry.getKey().numbers().contains(hit))
                cornerBetsLastOccurences.put(entry.getKey(),spinCount);
        }
    }

    public static void updateUserBets(Integer lastWinningNumber) {
        userBets = new ArrayList<>();
        cornerBetsLastOccurences.entrySet().stream()
                .filter(entry -> entry.getValue() < spinCount - nonOccurrenceCountThreshold)
                .limit(4)
                .forEach(entry -> userBets.add(new UserBet(entry.getKey().bet(), minBet)));
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
