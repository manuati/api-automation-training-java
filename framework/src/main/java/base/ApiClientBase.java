package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import utils.StringUtils;

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

    public Response post(String url, Object payload, Map<String, String> headers) throws JsonProcessingException {
        RequestBody requestBody = createRequestFromObject(payload);
        Request.Builder builder = createBaseRequestBuilder(url, headers);
        builder.post(requestBody);

        return executeRequest(builder.build());
    }

    private RequestBody createRequestFromObject(Object payloadObject) throws JsonProcessingException {
        String mediaType = baseHeaders.get("Content-Type");

        if (payloadObject == null) {
            return RequestBody.create("", MediaType.get(mediaType));
        }

        ObjectMapper mapper = new ObjectMapper();
        return RequestBody.create(mapper.writeValueAsString(payloadObject), MediaType.get(mediaType));
    }

    public Response get(String url, Map<String, String> headers) {
        Request.Builder builder = createBaseRequestBuilder(url, headers);
        builder.get();

        return executeRequest(builder.build());
    }

    public Response executeRequest(Request request) {
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("An exception occurred while executing request " + request.url());
            throw new RuntimeException(e);
        }
    }

    private Request.Builder createBaseRequestBuilder(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().url(url);

        baseHeaders.forEach((key, value) -> builder.addHeader(key,value));

        if (headers != null) headers.forEach((key, value) -> builder.addHeader(key,value));

        return builder;
    }

    private RequestBody createRequest(String requestPayload) {
        String mediaType = baseHeaders.get("Content-Type");

        if (StringUtils.isEmpty(requestPayload)) {
            return RequestBody.create("", MediaType.get(mediaType));
        }

        return RequestBody.create(requestPayload, MediaType.get(mediaType));
    }

}
