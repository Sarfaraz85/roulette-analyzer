package com.me;


import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analyzer {
    static int winCtr = 0;
    static int winBCtr = 0;
    static int timesWin = 0;
    static int timesBWin = 0;
    static int lossCtr = 0;
    static int timesLoss = 0;
    static boolean isLastSpinWon = false;
    static List<Integer> win = Arrays.asList(0, 00, 4, 5, 6, 10, 13, 14, 15, 22, 23, 24, 28, 31, 32, 33, 2, 8, 11, 17, 20, 26, 29, 35);
    static List<Integer> winB = Arrays.asList(2, 8, 11, 17, 20, 26, 29, 35);

    static List<Integer> REL = Arrays.asList(12, 14, 16, 18);
    static List<Integer> REH = Arrays.asList(30, 32, 34, 36);
    static List<Integer> ROL = Arrays.asList(1, 3, 5, 7, 9);
    static List<Integer> ROH = Arrays.asList(19, 21, 23, 25, 27);

    static List<Integer> BEL = Arrays.asList(2, 4, 6, 8, 10);
    static List<Integer> BEH = Arrays.asList(20, 22, 24, 26, 28);
    static List<Integer> BOL = Arrays.asList(11, 13, 15, 17);
    static List<Integer> BOH = Arrays.asList(29, 31, 33, 35);

    static List<Integer> RL = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18);
    static List<Integer> RH = Arrays.asList(19, 21, 23, 25, 27, 30, 32, 34, 36);

    static List<Integer> BL = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17);
    static List<Integer> BH = Arrays.asList(20, 22, 24, 26, 28, 29, 31, 33, 35);

    static List<Integer> EL = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18);
    static List<Integer> EH = Arrays.asList(20, 22, 24, 26, 28, 30, 32, 34, 36);

    static List<Integer> OL = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17);
    static List<Integer> OH = Arrays.asList(19, 21, 23, 25, 27, 29, 31, 33, 35);

    static List<Integer> line_1to6 = Arrays.asList(1,2,3,4,5,6);
    static List<Integer> line_7to12 = Arrays.asList(7,8,9,10,11,12);
    static List<Integer> line_13to18 = Arrays.asList(13,14,15,16,17,18);
    static List<Integer> line_19to24 = Arrays.asList(19,20,21,22,23,24);
    static List<Integer> line_25to30 = Arrays.asList(25,26,27,28,29,30);
    static List<Integer> line_31to36 = Arrays.asList(31,32,33,34,35,36);

    static List<Integer> corner_3 = Arrays.asList(8,9,11,12,16,17,19,20,28,29,31,32);
    static List<Integer> corner_4 = Arrays.asList(10,11,12,13,14,15,22,23,25,26);




    static int bankroll = 2;

    public static void main(String[] args) throws IOException {
        List<Integer> bankrollProgression = Files.lines(Paths.get("/Users/nabyusuf/Downloads/10K.Real.Double0.txt"))
//                .limit(500)
                .peek(s -> trackBet(Integer.parseInt(s), corner_4))
//                .filter(s -> !isLastSpinWon && lossCtr > 0)
//                .map(s -> winCtr)
                .map(s -> lossCtr)
                .collect(Collectors.toList());
//        System.out.println(timesBWin);
        System.out.println(timesWin);
        System.out.println(timesLoss);
//        System.out.println(bankroll);


        Chart chart = new Chart(
                "Roulette strategy analyzer",
                "Bankroll progression",
                bankrollProgression);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
//        bankrollProgression.sort((o1, o2) -> o1 < o2 ? o1 : o2);
//        System.out.println(bankrollProgression);
        chart.setVisible(true);

    }

    public static void trackBet(int hit, List<Integer> bet) {
        int currBR = bankroll;
        if (bet.contains(hit)) {
            winCtr++;
            timesWin++;

            if (!isLastSpinWon && lossCtr > 0) {
//                System.out.println("lost spins: " + lossCtr + "| bankroll: " + currBR);
                lossCtr = 0;
            }
            isLastSpinWon = true;

        } else {
            lossCtr++;
            timesLoss++;
            if (isLastSpinWon && winCtr > 0) {
                System.out.println("Won  spins: " + winCtr + "| bankroll: " + currBR);
                winCtr = 0;
            }
            isLastSpinWon = false;
        }

    }

    public static void track(int hit) {
        int currBR = bankroll;
        if (win.contains(hit)) {
            if (winCtr > 1)
                bankroll += 2;
            winCtr++;
            timesWin++;
            if (winB.contains(hit)) {
                if (winCtr > 1)
                    bankroll += 18;
                winBCtr++;
                timesBWin++;
            }
            if (!isLastSpinWon && lossCtr > 0) {
                System.out.println("lost spins: " + lossCtr + "| bankroll: " + currBR);
                lossCtr = 0;
            }
            isLastSpinWon = true;

        } else {
            if (winCtr > 0)
                bankroll -= 16;
            lossCtr++;
            timesLoss++;
            if (isLastSpinWon && winCtr > 0) {
                System.out.println("Won  spins: " + winCtr + ", profit wins: " + winBCtr + "| bankroll: " + currBR);
                winCtr = 0;
                winBCtr = 0;
            }
            isLastSpinWon = false;
        }

    }
}
