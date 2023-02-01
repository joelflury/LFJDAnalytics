package logic.consumer;

import com.sun.jersey.api.client.ClientHandlerException;
import modell.*;

import java.util.ArrayList;
import java.util.List;

class LogicAdapter {
    public static SalesDataObject getSales(String firstDate, String lastDate) {
        try {
            return (SalesDataObject) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getSales", new DateRangeDataObject(firstDate, lastDate), SalesDataObject.class);
        }catch (ClientHandlerException e){
            return new SalesDataObject(new ArrayList<>());
        }
    }

    public static ArticleDataObject getArticles() {
        try {
            return (ArticleDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getArticles", ArticleDataObject.class);
        }catch (ClientHandlerException e){
        return new ArticleDataObject(new ArrayList<>());
    }catch (Exception e){
        System.out.println(e);
        return null;
    }
    }

    public static DateRangeDataObject getDates() {
        try{
        return (DateRangeDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getDates", DateRangeDataObject.class);
        }catch (ClientHandlerException e) {
            return new DateRangeDataObject();
        }
        }

    public static int getUserData(String username, String password) {
        try{
        return (Integer) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getUserData", new UserDataObject(username, password), Integer.class);
    }catch (ClientHandlerException e){
        return 3;
    }catch (Exception e){
        return 0;
    }
    }

}
