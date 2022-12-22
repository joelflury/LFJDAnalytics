package logic.consumer;

class Consumer {
  public static void main(String[] args) {
    SalesDataObject sales = LogicAdapter.getSales();
    System.out.println(sales.getArticlePerDay());

    ArticleDataObject articles = LogicAdapter.getArticles();
    System.out.println(articles.getArticles());


  }
}
