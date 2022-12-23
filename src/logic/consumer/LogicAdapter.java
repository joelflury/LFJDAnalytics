package logic.consumer;

class LogicAdapter {
  public static SalesDataObject getSales() {
    return (SalesDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getSales", SalesDataObject.class);
  }

  public static ArticleDataObject getArticles() {
    return (ArticleDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getArticles", ArticleDataObject.class);
  }
}
