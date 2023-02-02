package logic.algorithm;

import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;


public class SalesForcecastAlgorithm {
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private List<Article> articles;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SalesForcecastAlgorithm(LocalDate lastDate, List<Article> articles) {
        this.fromDate = LocalDate.now();
        this.toDate = lastDate;
        this.articles = articles;
    }

    public SalesForcecastAlgorithm(LocalDate firstDate, LocalDate lastDate, List<Article> articles) {
        this.fromDate = firstDate;
        this.toDate = lastDate;
        this.articles = articles;
    }

    public void calculate() {
        for (Article article : articles) {
            int amountofDays = (int) ChronoUnit.DAYS.between(fromDate, toDate);
            List<SalesPerDay> salesLast7days = Consumer.getSales().getArticlePerDay(fromDate.minusDays(8), fromDate.minusDays(1), article);
            List<SalesPerDay> salesLast365days = Consumer.getSales().getArticlePerDay(fromDate.minusDays(365), fromDate.minusDays(1), article);
            List<SalesPerDay> salesThisYear = Consumer.getSales().getArticlePerDay(LocalDate.now().with(firstDayOfYear()), fromDate, article);
            List<SalesPerDay> salesLastYear = Consumer.getSales().getArticlePerDay(LocalDate.now().minusDays(365).with(firstDayOfYear()), LocalDate.now().minusDays(365), article);
            List<SalesPerDay> sales2YearAgo = Consumer.getSales().getArticlePerDay(LocalDate.now().minusDays(730).with(firstDayOfYear()), LocalDate.now().minusDays(730), article);
            double average7d = calculateAverage(salesLast7days);
            List<Double> differencePerWeekday = calculateDifferenceWeekDay(salesLast365days);
            double differenceLastYear = calculateAverage(salesLastYear) / calculateAverage(salesThisYear);
            double difference2YearAgo = calculateAverage(sales2YearAgo) / calculateAverage(salesThisYear);
            for (int i = 1; i <= amountofDays; i++) {
                LocalDate date = LocalDate.now().plusDays(i);
                List<SalesPerDay> salesLastYear20days = Consumer.getSales().getArticlePerDay(date.minusDays(370), date.minusDays(360), article);
                List<SalesPerDay> sales2YearAgo20days = Consumer.getSales().getArticlePerDay(date.minusDays(735), date.minusDays(725), article);
                double averageLastYear20Days = calculateAverage(salesLastYear20days);
                double average2YearAgo20Days = calculateAverage(sales2YearAgo20days);
                int predictedSalesamount = (int) Math.round(((2 * average7d + (3 * averageLastYear20Days * Math.pow(differenceLastYear, 2)) + (average2YearAgo20Days * difference2YearAgo)) / 6) * differencePerWeekday.get(Util.getWeekDay(date) - 1));
                float price = predictedSalesamount * article.getPrice();
                new SalesPerDay(article.getArticleID(), date.toString(), predictedSalesamount, price, true);
            }
        }
    }

    private List<Double> calculateDifferenceWeekDay(List<SalesPerDay> salesLast365days) {
        double average365d = calculateAverage(salesLast365days);
        List<Double> differencePerWeekday = new ArrayList();
        for (int i = 1; i <= 7; i++) {
            double difference = average365d / calculateAverage(salesLast365days, i);
            differencePerWeekday.add(difference);
        }
        return differencePerWeekday;
    }

    private double calculateAverage(List<SalesPerDay> salesList) {
        double average = 0;
        double dataAmount = 0;
        for (SalesPerDay sale : salesList) {
            average += sale.getAmount();
            dataAmount++;
        }
        return average / dataAmount;
    }

    private double calculateAverage(List<SalesPerDay> salesList, int weekDay) {
        double average = 0;
        double dataAmount = 0;
        for (SalesPerDay sale : salesList) {
            LocalDate date = LocalDate.parse(sale.getDate(), dtf);
            if (Util.getWeekDay(date) == weekDay) {
                average += sale.getAmount();
                dataAmount++;
            }
        }
        return average / dataAmount;
    }


}
