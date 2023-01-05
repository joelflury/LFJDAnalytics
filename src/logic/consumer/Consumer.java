package logic.consumer;

import modell.*;

import java.util.List;

public class Consumer {

    private static ArticleDataObject articles;
    private static int userRet;
    private static DateRangeDataObject dateRange;
    private static SalesDataObject sales;

    public void start() {
        userRet = LogicAdapter.getUserData("dbo", "password");
        articles = LogicAdapter.getArticles();
        dateRange = LogicAdapter.getDates();
        sales = LogicAdapter.getSales(dateRange.getFirstDate(), dateRange.getLastDate());
    }

    public static ArticleDataObject getArticleData() {
        return articles;
    }

    public static int getUserRet() {
        return userRet;
    }

    public static DateRangeDataObject getDateRange() {
        return dateRange;
    }

    public static SalesDataObject getSales() {
        return sales;
    }
}
