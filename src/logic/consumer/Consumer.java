package logic.consumer;

import modell.*;
import java.time.LocalDate;

public class Consumer {
    private static ArticleDataObject articles;
    private static SalesDataObject sales;

    public void getSalesData(LocalDate fromDate, LocalDate toDate) {
        sales = LogicAdapter.getSales(String.valueOf(fromDate), String.valueOf(toDate));
    }

    public void getArticleData() {
        articles = LogicAdapter.getArticles();
    }

    public int getUserdata(String userName, String password) {
        return LogicAdapter.getUserData(userName, password);
    }

    public static ArticleDataObject getArticles() {
        return articles;
    }

    public static DateRangeDataObject getDateRange() {
        return LogicAdapter.getDates();
    }

    public static SalesDataObject getSales() {
        return sales;
    }
}
