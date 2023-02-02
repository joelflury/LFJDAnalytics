package modell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesPerDay {
    private final int articleID;
    private final String date;
    private final int amount;
    private final float price;

    private static final List<SalesPerDay> salesPerDayList = new ArrayList<>();
    private static final List<SalesPerDay> salesForecastList = new ArrayList<>();

    public SalesPerDay(int articleID, String date, int amount, float price, boolean prediction) {
        this.articleID = articleID;
        this.date = date;
        this.price = price;
        this.amount = amount;
        if (!prediction) {
            salesPerDayList.add(this);
        } else {
            salesForecastList.add(this);
        }
    }

    public int getArticleID() {
        return articleID;
    }

    public String getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public static void setSalesPerDayList(SalesPerDay salesPerDay) {
        SalesPerDay.salesPerDayList.add(salesPerDay);
    }

    public static void clearArticlePerDayList() {
        salesPerDayList.clear();
    }

    public static List<SalesPerDay> getSalesPerDayList() {
        return salesPerDayList;
    }

    public static List<SalesPerDay> getSalesForecastList() {
        return salesForecastList;
    }

    public static List<SalesPerDay> getSalesForecastList(LocalDate fromDate, LocalDate toDate) {
        List<SalesPerDay> tempSalesForecastList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (SalesPerDay sale : salesForecastList) {
            LocalDate date = LocalDate.parse(sale.getDate(), dtf);
            if (date.isAfter(fromDate.minusDays(1)) && date.isBefore(toDate.plusDays(1))) {
                tempSalesForecastList.add(sale);
            }
        }

        return tempSalesForecastList;
    }

    @Override
    public String toString() {
        return "ArticlePerDay{" +
                "articleID=" + articleID +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

}
