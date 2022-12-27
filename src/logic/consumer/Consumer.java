package logic.consumer;

class Consumer {
  public static void main(String[] args) {
    SalesDataObject sales = LogicAdapter.getSales("2023-09-10 00:00:00", "2023-10-10 00:00:00");
    System.out.println(sales.getArticlePerDay());

    ArticleDataObject articles = LogicAdapter.getArticles();
    System.out.println(articles.getArticles());

    DateDataObject dateRange = LogicAdapter.getDates();
    System.out.println(dateRange.getFirstDate());
    System.out.println(dateRange.getLastDate());

  }
}
