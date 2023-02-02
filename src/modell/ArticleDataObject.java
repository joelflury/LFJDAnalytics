package modell;

import java.util.List;

public class ArticleDataObject {
    private final List<Article> articles;

    public ArticleDataObject(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
