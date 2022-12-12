/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Implementation von GET-Requests.
 */

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

class RestClientGET {
  static Object request(String url, String path, Class classDesc) {
    Client create = Client.create();
    WebResource service = create.resource(url);
    Gson gson = new Gson();

    String response = service.path(path)
        .type(MediaType.APPLICATION_JSON)
        .get(String.class);

    return gson.fromJson(response,  classDesc);
  }
}
