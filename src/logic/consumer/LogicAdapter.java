package logic.consumer;

import com.sun.jersey.api.client.ClientHandlerException;
import modell.*;
import java.util.ArrayList;

class LogicAdapter {
    /**
     * Retrieve Salesdata from Backend
     * @param fromDate Firstdate to be retrieved
     * @param toDate    Lastdate to be retrieved
     * @return SalesDataObject which contains List of SalesperDay
     */
    public static SalesDataObject getSales(String fromDate, String toDate) {
        try {
            return (SalesDataObject) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getSales", new DateRangeDataObject(fromDate, toDate), SalesDataObject.class);
        } catch (ClientHandlerException e) {
            return new SalesDataObject(new ArrayList<>());
        }
    }

    /**
     * Retrieve Articles from Backend
     * @return  ArticleDataObject which contains List of Articles
     */
    public static ArticleDataObject getArticles() {
        try {
            return (ArticleDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getArticles", ArticleDataObject.class);
        } catch (ClientHandlerException e) {
            return new ArticleDataObject(new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Retrieve first and lasdate orderdate from Backend
     * @return DateRangeObject which contains first and last orderdate
     */
    public static DateRangeDataObject getDates() {
        try {
            return (DateRangeDataObject) RestClientGET.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getDates", DateRangeDataObject.class);
        } catch (ClientHandlerException e) {
            return new DateRangeDataObject();
        }
    }

    /**
     * Check User Credentials on Backend
     * @param username  Username to check
     * @param password  Hashed Password to be checked
     * @return  status of authentication (0=user not found, 1=password incorrect, 2=authentication successful, 3=no connection to backend)
     */
    public static int getUserData(String username, String password) {
        try {
            return (Integer) RestClientPOST.request("http://az-srv01.switzerlandnorth.cloudapp.azure.com", "/getUserData", new UserDataObject(username, password), Integer.class);
        } catch (ClientHandlerException e) {
            return 3;
        } catch (Exception e) {
            return 0;
        }
    }

}
