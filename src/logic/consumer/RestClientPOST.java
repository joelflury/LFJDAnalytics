package logic.consumer;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

class RestClientPOST {
    private static final Client create = Client.create();

    /**
     * Function to send HTTP POST-Request
     * @param url URL to API
     * @param path Path to API
     * @param object Object to send in body
     * @param classDesc  Class to be used to convert response into
     * @return HTTP Response in Class
     */
    static Object request(String url, String path, Object object, Class classDesc) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        String response = service.path(path).type(MediaType.APPLICATION_JSON)
                .post(String.class, json);

        return gson.fromJson(response, classDesc);
    }
}
