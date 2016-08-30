package com.me;

import com.me.model.UserBet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.Arrays;
import java.util.List;

import static com.me.model.BetType.LOW;
import static com.me.model.BetType.RED;

public class Chart extends ApplicationFrame {
    private static int bankRoll = 5;
    private static int minBet = 5;
    private static BetProcessor betProcessor = new BetProcessor();
//    private static List<UserBet> userBets = Arrays.asList(
//            new UserBet(RED, minBet)
//            , new UserBet(LOW, minBet)
////            , new UserBet(DOZEN1, minBet)
////            , new UserBet(ODD, minBet)
//    );
    int index = 0;

    public Chart(String applicationTitle, String chartTitle, List<Integer> data, int initialBankroll) {
        super(applicationTitle);
        bankRoll=initialBankroll;
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Spin", "Bankroll",
                createDataset(data),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1600, 900));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(List<Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.addValue(100, "bankroll", "1");
//        dataset.addValue(95, "bankroll", "2");
//        dataset.addValue(90, "bankroll", "3");
//        dataset.addValue(100, "bankroll", "4");
//        dataset.addValue(105, "bankroll", "5");
//        dataset.addValue(110, "bankroll", "6");


        data.stream()
//                .peek(s -> System.out.println("*******\ntotal amount wagered = " + userBets.stream().mapToInt(b -> b.wager).sum()))
//                .peek(s -> bankRoll -= userBets.stream().mapToInt(b -> b.wager).sum())
//                .peek(s -> System.out.println("current bankroll = " + bankRoll))
//                .peek(s -> System.out.println("number hit = " + s))
//                .map(n -> betProcessor.userWinAmount(n, userBets))
//                .peek(s -> System.out.println("win amount = " + s))
//                .peek(n -> bankRoll += betProcessor.userWinAmount(n, userBets))
//                .peek(s -> System.out.println("new bankroll = " + s))
                .forEach(n -> {
                    ++index;
                    dataset.addValue((Number) n, "Running bankroll", index);
                    dataset.addValue((Number) bankRoll, "Starting bankroll", index);
                    dataset.addValue((Number) (bankRoll * 2), "Target bankroll", index);
                    dataset.addValue((Number) 0, "Zero", index);
                });
        return dataset;
    }

//    public static void main(String[] args) {
//        Chart chart = new Chart(
//                "Roulette strategy analyzer",
//                "Bankroll progression");
//
//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
//    }
}