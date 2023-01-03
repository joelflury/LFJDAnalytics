package logic.consumer;

import modell.Article;
import modell.ArticleDataObject;
import modell.DateRangeDataObject;
import modell.SalesDataObject;

public class Consumer {

    public void start() {
//        SalesDataObject sales = LogicAdapter.getSales("2022-09-10", "2023-10-10");
//        System.out.println(sales.getArticlePerDay());

        ArticleDataObject articles = LogicAdapter.getArticles();
        for (Article article:articles.getArticles()) {
            Article.setArticles(article);
        }

//        DateRangeDataObject dateRange = LogicAdapter.getDates();
//        System.out.println(dateRange.getFirstDate());
//        System.out.println(dateRange.getLastDate());
    }

}
