package modell;

import java.util.ArrayList;
import java.util.List;

public class SalesPerWeek {
    private final int ARTICLEID;
    private final int WEEK;
    private int AMOUNT;
    private float PRICE;
    private static List<SalesPerWeek> salesPerWeekList = new ArrayList<>();

    public SalesPerWeek(int ARTICLEID, int WEEK, int AMOUNT, float PRICE) {
        this.ARTICLEID = ARTICLEID;
        this.WEEK = WEEK;
        this.PRICE = PRICE;
        this.AMOUNT = AMOUNT;
        salesPerWeekList.add(this);
    }

    public int getARTICLEID() {
        return ARTICLEID;
    }

    public int getWEEK() {
        return WEEK;
    }

    public float getPRICE() {
        return PRICE;
    }

    public int getAMOUNT() {
        return AMOUNT;
    }

    public static List<SalesPerWeek> getArticlePerDayList() {
        return salesPerWeekList;
    }

    public static void setSalesPerWeekList(SalesPerWeek salesPerDay) {
        SalesPerWeek.salesPerWeekList.add(salesPerDay);
    }

    public void setAMOUNT(int AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public void setPRICE(float PRICE) {
        this.PRICE = PRICE;
    }

    @Override
    public String toString() {
        return "ArticlePerDay{" +
                "ARTICLEID=" + ARTICLEID +
                ", WEEK='" + WEEK + '\'' +
                ", PRICE=" + PRICE +
                ", AMOUNT=" + AMOUNT +
                '}';
    }

}
