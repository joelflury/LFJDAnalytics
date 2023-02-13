package logic.consumer;

import modell.*;
import java.time.LocalDate;

public class Consumer {
    private static ArticleDataObject articles;
    private static SalesDataObject sales;

    /**
     * Retrieve Salesdata from Backend
     * @param fromDate Firstdate to be retrieved
     * @param toDate    Lastdate to be retrieved
     */
    public void getSalesData(LocalDate fromDate, LocalDate toDate) {
        sales = LogicAdapter.getSales(String.valueOf(fromDate), String.valueOf(toDate));
    }

    /**
     * Retrieve Articles from Backend
     */
    public void getArticleData() {
        articles = LogicAdapter.getArticles();
    }

    /**
     * Check User and Password on Backend
     * @param userName  Username to be checked
     * @param password  Password as Hashvalue to be checked
     * @return status of authentication (0=user not found, 1=password incorrect, 2=authentication successful, 3=no connection to backend)
     */
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
