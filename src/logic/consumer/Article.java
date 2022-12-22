package logic.consumer;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private int articleID;
    private String articlename;
    private float price;

    private static List<Article> articles = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Article{" +
                "articleID=" + articleID +
                ", articlename='" + articlename + '\'' +
                ", price=" + price +
                '}';
    }
}
