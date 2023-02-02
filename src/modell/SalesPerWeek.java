package modell;

import java.util.ArrayList;
import java.util.List;

public class SalesPerWeek {
    private final int articleID;
    private final int week;
    private int amount;
    private float price;
    private static final List<SalesPerWeek> salesPerWeekList = new ArrayList<>();

    public SalesPerWeek(int articleID, int week, int amount, float price) {
        this.articleID = articleID;
        this.week = week;
        this.price = price;
        this.amount = amount;
        salesPerWeekList.add(this);
    }

    public int getArticleID() {
        return articleID;
    }

    public int getWeek() {
        return week;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public static List<SalesPerWeek> getArticlePerDayList() {
        return salesPerWeekList;
    }

    public static void setSalesPerWeekList(SalesPerWeek salesPerDay) {
        SalesPerWeek.salesPerWeekList.add(salesPerDay);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ArticlePerDay{" +
                "articleID=" + articleID +
                ", week='" + week + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

}
