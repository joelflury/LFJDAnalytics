package modell;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private final int articleID;
    private final String articlename;
    private final float price;

    private static final List<Article> articles = new ArrayList<>();

    public Article(int articleID, String articlename, float price) {
        this.articleID = articleID;
        this.articlename = articlename;
        this.price = price;
        articles.add(this);
    }

    public int getArticleID() {
        return articleID;
    }

    public String getArticlename() {
        return articlename;
    }

    public float getPrice() {
        return price;
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
                "articleID=" + articleID +
                ", articlename='" + articlename + '\'' +
                ", price=" + price +
                '}';
    }
}
