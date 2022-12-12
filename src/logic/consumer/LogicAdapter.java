/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Stellt den LogikAdapter zur Verfügung und verbirgt die REST-Details.
 */

import java.util.HashMap;
import java.util.Map;

class LogicAdapter {
  public static DataObject getData() {
    return (DataObject)RestClientGET.request("http://localhost", "/producer/data", DataObject.class);
    //Alternative: "http://localhost:81"
  }

  public static DataObject getData(String name) {
    return (DataObject)RestClientGET.request("http://localhost", "/producer/data/" + name, DataObject.class);
  }

  public static void setData(DataObject data) {
    RestClientPOST.request("http://localhost", "/producer/data", data);
  }

  public static DataObject calculate(DataObject data, int queryParam1, String queryParam2) {
    Map queryParams = new HashMap();
    queryParams.put("queryParam1", Integer.toString(queryParam1));
    queryParams.put("queryParam2", queryParam2);

    return (DataObject)RestClientPOST.request("http://localhost", "/producer/data2", queryParams, data);
    //return (DataObject)RestClientPOST.request("http://localhost", "/producer/data2", queryParams, data, DataObject.class);
  }
}
