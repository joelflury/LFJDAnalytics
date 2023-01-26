package util;

import logic.algorithm.SalesForcecastAlgorithm;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestAlgorithm {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static Consumer consumer = new Consumer();
    static LocalDate lastDate = LocalDate.parse(consumer.getDateRange().getLastDate(), dtf);
    static LocalDate firstDate = LocalDate.now().minusDays(90);
    static SalesForcecastAlgorithm algorithm = new SalesForcecastAlgorithm(firstDate, lastDate, Article.getArticles());

    public static void test(){
        algorithm.calculate();
        testPerDay();
        testPerArticle();
    }

    // Test the Algorithm to calculate the deviation per article between real sales and predicted sales
    private static void testPerArticle(){
        consumer.getSalesData(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31));
        List<SalesPerDay> realSales = consumer.getSales().getArticlePerDay();
        List<SalesPerDay> predictedSales = SalesPerDay.getSalesForecastList(LocalDate.now().plusDays(1), lastDate);
        Double deviationAverage = 0.0;
        int articleAmount = 0;
        //Loop trough all articles and calculate the deviation between real sales and predicted sales
        for (Article article:Consumer.getArticles().getArticles()){
            System.out.println(article.getArticlename());
            int deviation;
                int realAmount = 0;
                int predictedAmount = 0;
                for (SalesPerDay sale: realSales){
                    LocalDate saleDate = LocalDate.parse(sale.getDate(),dtf);
                    if (saleDate.isAfter(firstDate.minusDays(1)) && saleDate.isBefore(lastDate.plusDays(1))){
                        if (sale.getArticleID() == article.getArticleID()){
                            realAmount = realAmount + sale.getAmount();
                        }
                    }
                }
                for (SalesPerDay sale: predictedSales){
                    LocalDate saleDate = LocalDate.parse(sale.getDate(),dtf);
                    if (saleDate.isAfter(firstDate.minusDays(1)) && saleDate.isBefore(lastDate.plusDays(1))){
                        if (sale.getArticleID() == article.getArticleID()){
                            predictedAmount = predictedAmount + sale.getAmount();
                        }
                    }
                }
                deviation =  realAmount - predictedAmount;

            deviationAverage = deviationAverage + Math.abs(deviation);
            articleAmount++;
            System.out.println("Deviation: " + deviation);
        }
        deviationAverage = deviationAverage/ articleAmount;
        System.out.println("Average deviation: " + deviationAverage);
    }

    // Test the Algorithm to calculate the deviation per day between realsales and predicted sales
    private static void testPerDay(){
        consumer.getSalesData(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31));
        List<SalesPerDay> realSales = consumer.getSales().getArticlePerDay();
        List<SalesPerDay> predictedSales = SalesPerDay.getSalesForecastList(LocalDate.now().plusDays(1), lastDate);
        Double deviationAverage = 0.0;
        int salesAmount = 0;
        for (LocalDate date = LocalDate.now().plusDays(1); date.isBefore(lastDate.plusDays(1)); date = date.plusDays(1)){
            for (Article article:Consumer.getArticles().getArticles()){
                int realAmount = 0;
                int predictedAmount = 0;
                int deviation;
                for (SalesPerDay sale: realSales){
                    if (sale.getDate().equals(date.toString())){
                        if (sale.getArticleID() == article.getArticleID()){
                            realAmount = sale.getAmount();
                        }
                    }
                }
                for (SalesPerDay sale: predictedSales){
                    if (sale.getDate().equals(date.toString())){
                        if (sale.getArticleID() == article.getArticleID()){
                            predictedAmount = sale.getAmount();
                        }
                    }
                }
                deviation = Math.abs(realAmount - predictedAmount);
                deviationAverage = deviationAverage + deviation;
                if (deviation > 5){
                    System.out.println("-- High deviation --");
                    System.out.println(date);
                    System.out.println(article.getArticlename());
                    System.out.println("Real Amount: " + realAmount);
                    System.out.println("Predicted Amount: " + predictedAmount);
                    System.out.println("Deviation: " + deviation);
                }
                salesAmount++;
            }
        }
        deviationAverage = deviationAverage/salesAmount;
        System.out.println("Daterange: " + firstDate + " - " + lastDate);
        System.out.println("Average deviation: " + deviationAverage);
    }
}
