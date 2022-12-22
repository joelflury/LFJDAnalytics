package logic.consumer;
import java.util.ArrayList;
import java.util.List;

public class ArticlePerDay {
    private int articleID;
    private String date;
    private int amount;
    private float price;

    private static List<ArticlePerDay> articlePerDayList = new ArrayList<>();

    public ArticlePerDay(int articleID, String date, int amount, float price) {
        this.articleID = articleID;
        this.date = date;
        this.price = price;
        this.amount = amount;
        articlePerDayList.add(this);
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

    public static List<ArticlePerDay> getArticlePerDayList() {
        return articlePerDayList;
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
