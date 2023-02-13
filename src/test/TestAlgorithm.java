package test;

import logic.algorithm.SalesForcecastAlgorithm;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestAlgorithm {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static Consumer consumer = new Consumer();
    private static LocalDate fromDate = LocalDate.now().minusDays(90);
    private static LocalDate toDate = LocalDate.parse(Consumer.getDateRange().getTODATE(), dtf);
    private static SalesForcecastAlgorithm algorithm = new SalesForcecastAlgorithm(fromDate, toDate, Article.getArticles());

    public static void test() {
        algorithm.calculate();
        testPerDay();
        testPerArticle();
    }

    /**
     * Test the Algorithm to calculate the deviation per article between real sales and predicted sales
     */
    private static void testPerArticle() {
        consumer.getSalesData(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31));
        List<SalesPerDay> realSales = Consumer.getSales().getArticlePerDay();
        List<SalesPerDay> predictedSales = SalesPerDay.getSalesForecastList(LocalDate.now().plusDays(1), toDate);
        double deviationAverage = 0.0;
        int articleAmount = 0;
        System.out.println("Deviation Test per Article");
        System.out.println("Daterange: " + fromDate + " - " + toDate);
        //Loop through all articles and calculate the deviation between real sales and predicted sales
        for (Article article : Consumer.getArticles().getArticles()) {
            System.out.println(article.getARTICLENAME());
            int deviation;
            int realAmount = 0;
            int predictedAmount = 0;
            for (SalesPerDay sale : realSales) {
                LocalDate saleDate = LocalDate.parse(sale.getDATE(), dtf);
                if (saleDate.isAfter(fromDate.minusDays(1)) && saleDate.isBefore(toDate.plusDays(1))) {
                    if (sale.getARTICLEID() == article.getARTICLEID()) {
                        realAmount = realAmount + sale.getAMOUNT();
                    }
                }
            }
            for (SalesPerDay sale : predictedSales) {
                LocalDate saleDate = LocalDate.parse(sale.getDATE(), dtf);
                if (saleDate.isAfter(fromDate.minusDays(1)) && saleDate.isBefore(toDate.plusDays(1))) {
                    if (sale.getARTICLEID() == article.getARTICLEID()) {
                        predictedAmount = predictedAmount + sale.getAMOUNT();
                    }
                }
            }
            deviation = realAmount - predictedAmount;

            deviationAverage = deviationAverage + Math.abs(deviation);
            articleAmount++;
            System.out.println("Deviation: " + deviation);
        }
        deviationAverage = deviationAverage / articleAmount;
        System.out.println("Average deviation: " + deviationAverage);
        System.out.println();
    }

    /**
     * Test the Algorithm to calculate the deviation per day between realsales and predicted sales
     */
    private static void testPerDay() {
        consumer.getSalesData(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31));
        List<SalesPerDay> realSales = Consumer.getSales().getArticlePerDay();
        List<SalesPerDay> predictedSales = SalesPerDay.getSalesForecastList(LocalDate.now().plusDays(1), toDate);
        double deviationAverage = 0.0;
        int salesAmount = 0;
        for (LocalDate date = LocalDate.now().plusDays(1); date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)) {
            for (Article article : Consumer.getArticles().getArticles()) {
                int realAmount = 0;
                int predictedAmount = 0;
                int deviation;
                for (SalesPerDay sale : realSales) {
                    if (sale.getDATE().equals(date.toString())) {
                        if (sale.getARTICLEID() == article.getARTICLEID()) {
                            realAmount = sale.getAMOUNT();
                        }
                    }
                }
                for (SalesPerDay sale : predictedSales) {
                    if (sale.getDATE().equals(date.toString())) {
                        if (sale.getARTICLEID() == article.getARTICLEID()) {
                            predictedAmount = sale.getAMOUNT();
                        }
                    }
                }
                deviation = Math.abs(realAmount - predictedAmount);
                deviationAverage = deviationAverage + deviation;
                if (deviation > 5) {
                    System.out.println("-- High deviation --");
                    System.out.println(date);
                    System.out.println(article.getARTICLENAME());
                    System.out.println("Real Amount: " + realAmount);
                    System.out.println("Predicted Amount: " + predictedAmount);
                    System.out.println("Deviation: " + deviation);
                }
                salesAmount++;
            }
        }
        deviationAverage = deviationAverage / salesAmount;
        System.out.println("Daterange: " + fromDate + " - " + toDate);
        System.out.println("Average deviation: " + deviationAverage);
        System.out.println();
    }
}
