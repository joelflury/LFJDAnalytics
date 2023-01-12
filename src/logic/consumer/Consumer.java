package logic.consumer;

import modell.*;

import java.time.LocalDate;

public class Consumer {

    private static ArticleDataObject articles;
    private static DateRangeDataObject dateRange;
    private static SalesDataObject sales;

    public void getData(LocalDate startDate, LocalDate endDate) {
        articles = LogicAdapter.getArticles();
        long startTime = System.currentTimeMillis();
        sales = LogicAdapter.getSales(String.valueOf(startDate), String.valueOf(endDate));
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }

    public int getUserdata(String userName, String password){
        return LogicAdapter.getUserData(userName, password);
    }

    public static ArticleDataObject getArticleData() {
        return articles;
    }

    public static DateRangeDataObject getDateRange() {
        return dateRange;
    }

    public static SalesDataObject getSales() {
        return sales;
    }
}
