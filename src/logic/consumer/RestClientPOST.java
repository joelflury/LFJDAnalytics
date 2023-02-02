package logic.consumer;/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Implementation von POST-Requests.
 */

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;

class RestClientPOST {
    private static final Client create = Client.create();

    static void request(String url, String path, Object object) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        String response = service.path(path)
                .type(MediaType.APPLICATION_JSON)
                .post(String.class, json);
    }

    // wenn Übergabe- und Rückgabeobjekt vom gleichen Typ sind
    static Object request(String url, String path, Map queryParams, Object object) {
        return request(url, path, queryParams, object, object.getClass());
    }

    static Object request(String url, String path, Map queryParams, Object object, Class classDesc) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        MultivaluedMap params = new MultivaluedMapImpl();
        for (Object key : queryParams.keySet()) {
            params.add(key, queryParams.get(key));
        }

        String response = service.path(path).queryParams(params)
                .type(MediaType.APPLICATION_JSON)
                .post(String.class, json);

        return gson.fromJson(response, classDesc);
    }

    static Object request(String url, String path, String data, Class classDesc) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();

        String response = service.path(path).type(MediaType.APPLICATION_JSON)
                .post(String.class, data);

        return gson.fromJson(response, classDesc);
    }

    static Object request(String url, String path, Object object, Class classDesc) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        String response = service.path(path).type(MediaType.APPLICATION_JSON)
                .post(String.class, json);

        return gson.fromJson(response, classDesc);
    }
}
