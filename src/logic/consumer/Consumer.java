package logic.consumer;

import modell.*;

public class Consumer {

    public void start() {

        ArticleDataObject articles = LogicAdapter.getArticles();
        for (Article article : articles.getArticles()) {
            Article.setArticles(article);
        }

        DateRangeDataObject dateRange = LogicAdapter.getDates();


        SalesDataObject sales = LogicAdapter.getSales(dateRange.getFirstDate(), dateRange.getLastDate());
        for (SalesPerDay salesPerDay : sales.getArticlePerDay()) {
            SalesPerDay.setSalesPerDayList(salesPerDay);
        }
    }

}
