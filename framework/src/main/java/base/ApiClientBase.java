package base;
import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiClientBase {

    private Map<String, String> baseHeaders;

    protected ApiClientBase() {
        baseHeaders = new HashMap<>();
        baseHeaders.put("Content-Type", "application/json");
        baseHeaders.put("Accept","application/json");
    }

    public io.restassured.response.Response get(String url, Map<String, String> headers) {
        Map<String, String> finalHeaders = createFinalHeaders(headers);

        return RestAssured.with().headers(finalHeaders).get(url);
    }

    public io.restassured.response.Response put(String url, Object payload, Map<String, String> headers) {
        Map<String, String> finalHeaders = createFinalHeaders(headers);

        return RestAssured.with().body(payload).headers(finalHeaders).put(url);
    }

    public io.restassured.response.Response post(String url, Object payload, Map<String, String> headers) {
        Map<String, String> finalHeaders = createFinalHeaders(headers);

        return RestAssured.with().body(payload).headers(finalHeaders).post(url);
    }

    private Map<String, String> createFinalHeaders(Map<String, String> headers) {
        Map<String, String> finalHeaders = new HashMap<>(baseHeaders);

        if (headers != null) {
            finalHeaders.putAll(headers);
        }

        return finalHeaders;
    }


}
