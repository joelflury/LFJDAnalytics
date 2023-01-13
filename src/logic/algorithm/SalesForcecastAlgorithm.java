package logic.algorithm;

import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;


public class SalesForcecastAlgorithm {
    private final LocalDate firstDate;
    private final LocalDate lastDate;
    private List<Article> articles;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SalesForcecastAlgorithm( LocalDate lastDate, List<Article> articles) {
        this.firstDate = LocalDate.now();
        this.lastDate = lastDate;
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void calculate(){
        for (Article article:articles) {
            int amountofDays = (int) ChronoUnit.DAYS.between(firstDate, lastDate);
            List<SalesPerDay> salesLast7days = Consumer.getSales().getArticlePerDay(firstDate.minusDays(8), firstDate.minusDays(1), article);
            List<SalesPerDay> salesLast365days = Consumer.getSales().getArticlePerDay(firstDate.minusDays(365), firstDate.minusDays(1), article);
            List<SalesPerDay> salesOneYearAgo = Consumer.getSales().getArticlePerDay(firstDate.minusDays(365), lastDate.minusDays(365), article);
            List<SalesPerDay> salesThisYear = Consumer.getSales().getArticlePerDay(LocalDate.now().with(firstDayOfYear()), firstDate, article);
            List<SalesPerDay> salesLastYear = Consumer.getSales().getArticlePerDay(LocalDate.now().minusDays(365).with(firstDayOfYear()), LocalDate.now().minusDays(365).with(lastDayOfYear()), article);
            double average7d = calculateAverage(salesLast7days);
            List<Double> averagePerWeekday = new ArrayList();
            for (int i = 1; i <= 7; i++){
                averagePerWeekday.add(calculateAverage(salesLast365days, i));
            }

            double differenceYears = calculateAverage(salesLastYear) /calculateAverage(salesThisYear);
            System.out.println(amountofDays);
            System.out.println(salesOneYearAgo.size());
            System.out.println(article.getArticlename());
            System.out.println(article.getArticleID());
            for (int i = 1; i <= amountofDays; i++) {
                LocalDate date = LocalDate.now().plusDays(i);


                int predictedSalesamount = (int) ((average7d + salesOneYearAgo.get(i-1).getAmount() + averagePerWeekday.get(getWeekDay(date)-1) / 3) * differenceYears);
                //int predictedSalesamount = (int) ((average7d + averagePerWeekday.get(getWeekDay(date)-1) / 2) * differenceYears);
                float price = predictedSalesamount * article.getPrice();
                new SalesPerDay(article.getArticleID(),date.toString(), predictedSalesamount, price, true);
            }
        }

    }

    private double calculateAverage(List<SalesPerDay> salesList){
        double average = 0;
        double dataAmount = 0;
        for (SalesPerDay sale:salesList) {
            average += sale.getAmount();
            dataAmount++;
        }
        return average / dataAmount;
    }

    private double calculateAverage(List<SalesPerDay> salesList, int weekDay){
        double average = 0;
        double dataAmount = 0;
        for (SalesPerDay sale:salesList) {
            LocalDate date = LocalDate.parse(sale.getDate(), dtf);
            if(getWeekDay(date) == weekDay){
                average += sale.getAmount();
                dataAmount++;
            }
        }
        return average / dataAmount;
    }

    private int getWeekDay(LocalDate date){
        String weekDayString = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK)).toString();
        int weekDayInt = 0;

        switch (weekDayString) {
            case "MONDAY":
                weekDayInt = 1;
                break;
            case "TUESDAY":
                weekDayInt = 2;
                break;
            case "WEDNESDAY":
                weekDayInt = 3;
                break;
            case "THURSDAY":
                weekDayInt = 4;
                break;
            case "FRIDAY":
                weekDayInt = 5;
                break;
            case "SATURDAY":
                weekDayInt = 6;
                break;
            case "SUNDAY":
                weekDayInt = 7;
                break;
        }
        return weekDayInt;
    }
}
