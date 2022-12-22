package logic.consumer;

import java.util.List;

public class SalesDataObject {
    private List<ArticlePerDay> articlePerDay;

    public SalesDataObject(List<ArticlePerDay> articlePerDay){
        this.articlePerDay = articlePerDay;
    }

    public List<ArticlePerDay> getArticlePerDay() {
        return articlePerDay;
    }

}
