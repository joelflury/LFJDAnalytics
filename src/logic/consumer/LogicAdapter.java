package logic.consumer;

import modell.ArticleDataObject;
import modell.DateRangeDataObject;
import modell.SalesDataObject;
import modell.UserDataObject;

class LogicAdapter {
    public static SalesDataObject getSales(String firstDate, String lastDate) {
        return (SalesDataObject) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getSales", new DateRangeDataObject(firstDate, lastDate), SalesDataObject.class);
    }

    public static ArticleDataObject getArticles() {
        return (ArticleDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getArticles", ArticleDataObject.class);
    }

    public static DateRangeDataObject getDates() {
        return (DateRangeDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getDates", DateRangeDataObject.class);
    }

    public static int getUserData(String username, String password) {
        return (Integer) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getUserData", new UserDataObject(username, password), Integer.class);
    }

}
