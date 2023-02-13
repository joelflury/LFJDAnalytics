package modell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesPerDay {
    private final int ARTICLEID;
    private final String DATE;
    private final int AMOUNT;
    private final float PRICE;

    private static final List<SalesPerDay> salesPerDayList = new ArrayList<>();
    private static final List<SalesPerDay> salesForecastList = new ArrayList<>();

    public SalesPerDay(int ARTICLEID, String DATE, int AMOUNT, float PRICE, boolean prediction) {
        this.ARTICLEID = ARTICLEID;
        this.DATE = DATE;
        this.PRICE = PRICE;
        this.AMOUNT = AMOUNT;
        if (!prediction) {
            salesPerDayList.add(this);
        } else {
            salesForecastList.add(this);
        }
    }

    public int getARTICLEID() {
        return ARTICLEID;
    }

    public String getDATE() {
        return DATE;
    }

    public float getPRICE() {
        return PRICE;
    }

    public int getAMOUNT() {
        return AMOUNT;
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
            LocalDate date = LocalDate.parse(sale.getDATE(), dtf);
            if (date.isAfter(fromDate.minusDays(1)) && date.isBefore(toDate.plusDays(1))) {
                tempSalesForecastList.add(sale);
            }
        }

        return tempSalesForecastList;
    }

    @Override
    public String toString() {
        return "ArticlePerDay{" +
                "ARTICLEID=" + ARTICLEID +
                ", DATE='" + DATE + '\'' +
                ", PRICE=" + PRICE +
                ", AMOUNT=" + AMOUNT +
                '}';
    }

}
