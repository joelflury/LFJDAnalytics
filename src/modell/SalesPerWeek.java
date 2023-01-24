package modell;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesPerWeek {
    private int articleID;
    private String date;
    private int amount;
    private float price;
    private static List<SalesPerWeek> salesPerWeekList = new ArrayList<>();

    public SalesPerWeek(int articleID, String date, int amount, float price, boolean prediction) {
        this.articleID = articleID;
        this.date = date;
        this.price = price;
        this.amount = amount;
        salesPerWeekList.add(this);
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

    public static List<SalesPerWeek> getArticlePerDayList() {
        return salesPerWeekList;
    }

    public static void setSalesPerWeekList(SalesPerWeek salesPerDay) {
        SalesPerWeek.salesPerWeekList.add(salesPerDay);
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
