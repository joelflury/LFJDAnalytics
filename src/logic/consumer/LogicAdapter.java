package logic.consumer;

class LogicAdapter {
  public static SalesDataObject getSales() {
    return (SalesDataObject) RestClientGET.request("http://localhost", "/getSales", SalesDataObject.class);
  }

  public static ArticleDataObject getArticles() {
    return (ArticleDataObject) RestClientGET.request("http://localhost", "/getArticles", ArticleDataObject.class);
  }
}
