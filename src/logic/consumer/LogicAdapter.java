package logic.consumer;

import java.util.HashMap;
import java.util.Map;

class LogicAdapter {
  public static SalesDataObject getSales(String firstDate, String lastDate) {
    String data = "{" +
            "\"firstDate\": \"" + firstDate + "\"," +
            "\"lastDate\": \"" + lastDate + "\""+
            "}";
    return (SalesDataObject) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getSales", data, SalesDataObject.class);
  }

  public static ArticleDataObject getArticles() {
    return (ArticleDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getArticles", ArticleDataObject.class);
  }
    public static DateDataObject getDates() {
    return (DateDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getDates", DateDataObject.class);
  }

}
