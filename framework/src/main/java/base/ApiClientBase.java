package base;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiClientBase {

    // Clase base del api client. En la version de typescript. La misma posee el cliente que hace las invocaciones a la URL
    // Esta clase deberia construir y poner a disposicion el client a utilizar para las invocaciones HTTP
    // Opciones de librerias: Jersey, OkHttp, Apache HttpClient, Java 11 HttpClient
    private Map<String, String> baseHeaders;

    private OkHttpClient client;

    protected ApiClientBase() {
        baseHeaders = new HashMap<>();
        baseHeaders.put("Content-Type", "application/json");
        baseHeaders.put("Accept","application/json");

        client = new OkHttpClient();
    }

    public Response executeRequest(String url, String method, JSONObject requestPayload, Map<String, String> headers) {
        RequestBody requestBody = createRequest(requestPayload);

        Request.Builder builder = new Request.Builder()
                .url(url)
                .method(method, requestBody);

        baseHeaders.forEach((key, value) -> builder.addHeader(key,value));
        if (headers != null) headers.forEach((key, value) -> builder.addHeader(key,value));

        Request request = builder.build();

        Response responseString = null;

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("An exception occurred while executing request " + url + " with method " + method + " and body " + requestBody.toString());
            throw new RuntimeException(e);
        }

    }

    private RequestBody createRequest(JSONObject requestPayload) {
        return RequestBody.create(requestPayload.toString(), MediaType.get("application/json"));
    }

    public Response post(String url, JSONObject payload, Map<String, String> defaultHeaders) {
        return executeRequest(url, "POST", payload, defaultHeaders);
    }
}
