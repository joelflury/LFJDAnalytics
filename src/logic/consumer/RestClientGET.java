package logic.consumer;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;


class RestClientGET {

    /**
     * Function to send HTTP GET-Request
     * @param url URL to API
     * @param path Path to API
     * @param classDesc Class to be used to convert response into
     * @return HTTP Response in Class
     */
    static Object request(String url, String path, Class classDesc) {
        Client create = Client.create();
        WebResource service = create.resource(url);
        Gson gson = new Gson();

        String response = service.path(path)
                .type(MediaType.APPLICATION_JSON)
                .get(String.class);

        return gson.fromJson(response, classDesc);
    }
}
