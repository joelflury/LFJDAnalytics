package logic.consumer;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

class RestClientPOST {
    private static final Client create = Client.create();

    static Object request(String url, String path, Object object, Class classDesc) {
        WebResource service = create.resource(url);
        Gson gson = new Gson();
        String json = gson.toJson(object);

        String response = service.path(path).type(MediaType.APPLICATION_JSON)
                .post(String.class, json);

        return gson.fromJson(response, classDesc);
    }
}
