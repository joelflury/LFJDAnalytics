package modell;
import java.util.ArrayList;
import java.util.List;

public class SalesPerDay {
    private int articleID;
    private String date;
    private int amount;
    private float price;

    private static List<SalesPerDay> salesPerDayList = new ArrayList<>();
    private static List<SalesPerDay> salesForecastList = new ArrayList<>();

    public SalesPerDay(int articleID, String date, int amount, float price, boolean prediction) {
        this.articleID = articleID;
        this.date = date;
        this.price = price;
        this.amount = amount;
        if (!prediction){
            salesPerDayList.add(this);
        }else{
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

    public static List<SalesPerDay> getArticlePerDayList() {
        return salesPerDayList;
    }

    public static void setSalesPerDayList(SalesPerDay salesPerDay) {
        SalesPerDay.salesPerDayList.add(salesPerDay);
    }

    public static List<SalesPerDay> getSalesForecastList() {
        return salesForecastList;
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
