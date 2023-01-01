package logic.consumer;

class Consumer {
  public static void main(String[] args) {
    SalesDataObject sales = LogicAdapter.getSales("2022-09-10", "2023-10-10");
    System.out.println(sales.getArticlePerDay());

    ArticleDataObject articles = LogicAdapter.getArticles();
    System.out.println(articles.getArticles());

    DateRangeDataObject dateRange = LogicAdapter.getDates();
    System.out.println(dateRange.getFirstDate());
    System.out.println(dateRange.getLastDate());

  }
}
