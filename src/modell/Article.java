package modell;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private int articleID;
    private String articleName;
    private double articlePrice;
    private static List<Article> articleList = new ArrayList<>();
    public Article(int articleID, String articleName, double articlePrice) {
        this.articleID = articleID;
        this.articleName = articleName;
        this.articlePrice = articlePrice;
    }
}
