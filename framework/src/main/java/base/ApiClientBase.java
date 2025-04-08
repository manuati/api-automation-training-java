package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiClientBase {

    private Map<String, String> baseHeaders;

    private OkHttpClient client;

    protected ApiClientBase() {
        baseHeaders = new HashMap<>();
        baseHeaders.put("Content-Type", "application/json");
        baseHeaders.put("Accept","application/json");

        client = new OkHttpClient();
    }

    private Response executeMethod(String url, String method, Object payload, Map<String, String> headers) throws JsonProcessingException {
        RequestBody requestBody = createRequest(payload);
        Request.Builder builder = createBaseRequestBuilder(url, headers);
        builder.method(method, requestBody);

        return executeRequest(builder.build());
    }

    private RequestBody createRequest(Object payloadObject) throws JsonProcessingException {
        String mediaType = baseHeaders.get("Content-Type");

        if (payloadObject == null) {
            return RequestBody.create("", MediaType.get(mediaType));
        }

        ObjectMapper mapper = new ObjectMapper();
        return RequestBody.create(mapper.writeValueAsString(payloadObject), MediaType.get(mediaType));
    }

    private RequestBody createRequest2(Object payloadObject) throws JsonProcessingException {
        String mediaType = baseHeaders.get("Content-Type");

        if (payloadObject == null) {
            return RequestBody.create("", MediaType.get(mediaType));
        }

        ObjectMapper mapper = new ObjectMapper();
        return RequestBody.create(mapper.writeValueAsString(payloadObject), MediaType.get(mediaType));
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

    public Response put(String url, Object payload, Map<String, String> headers) throws JsonProcessingException {
        return executeMethod(url, "PUT", payload, headers);
    }

    public Response post(String url, Object payload, Map<String, String> headers) throws JsonProcessingException {
        return executeMethod(url, "POST", payload, headers);
    }

    public Response get(String url, Map<String, String> headers) throws JsonProcessingException {
        return executeMethod(url, "GET", null, headers);
    }
}
