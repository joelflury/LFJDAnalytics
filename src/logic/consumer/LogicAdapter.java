package logic.consumer;

import java.util.HashMap;
import java.util.Map;

class LogicAdapter {
  public static SalesDataObject getSales(String firstDate, String lastDate) {
    String data = "{" +
            "\"firstDate\": \"" + firstDate + "\"," +
            "\"lastDate\": \"" + lastDate + "\""+
            "}";
    return (SalesDataObject) RestClientPOST.request("http://localhost", "/getSales", data, SalesDataObject.class);
  }

  public static ArticleDataObject getArticles() {
    return (ArticleDataObject) RestClientGET.request("http://localhost", "/getArticles", ArticleDataObject.class);
  }
    public static DateDataObject getDates() {
    return (DateDataObject) RestClientGET.request("http://localhost", "/getDates", DateDataObject.class);
  }

}
