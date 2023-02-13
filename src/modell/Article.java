package modell;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private final int ARTICLEID;
    private final String ARTICLENAME;
    private final float PRICE;
    private static List<Article> articles = new ArrayList<>();

    public Article(int articleID, String articlename, float PRICE) {
        this.ARTICLEID = articleID;
        this.ARTICLENAME = articlename;
        this.PRICE = PRICE;
        articles.add(this);
    }

    public int getARTICLEID() {
        return ARTICLEID;
    }

    public String getARTICLENAME() {
        return ARTICLENAME;
    }

    public float getPRICE() {
        return PRICE;
    }

    public static List<Article> getArticles() {
        return articles;
    }

    public static void clearArticles() {
        articles.clear();
    }

    @Override
    public String toString() {
        return "Article{" +
                "ARTICLEID=" + ARTICLEID +
                ", ARTICLENAME='" + ARTICLENAME + '\'' +
                ", PRICE=" + PRICE +
                '}';
    }
}
