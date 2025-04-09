package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.requests.CredentialModel;
import models.responses.ResponseContainer;
import models.responses.SessionResponse;
import okhttp3.Response;
import utils.ErrorMessages;
import utils.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceBase {

    protected ApiClient apiClient;
    protected String url;
    protected Map<String, String> defaultHeaders;

    public ServiceBase(String endpointPath) {
        this.apiClient = ApiClient.getInstace();
        this.url = baseUrl() + endpointPath;
        this.defaultHeaders = new HashMap<>();
    }

    private static String baseUrl() {
        String url = System.getenv("BASEURL");
        if (url == null) url = "";

        return url;
    }

    /**
     * Authenticates the user registered in the env vars to make calls against the API
     */
    public final void authenticate() throws RuntimeException, IOException {
        String username = System.getenv("USER");
        String password = System.getenv("PASSWORD");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException(ErrorMessages.MISSING_USERNAME_PASSWORD);
        }

        String token = SessionManager.getCachedToken(username, password);
        if (!StringUtils.isEmpty(token)) {
            saveCookieInHeaders(token);
            System.out.println("Token refreshed");
            return;
        }

        CredentialModel credentialRequest = new CredentialModel(username, password);

        ResponseContainer<SessionResponse> loginResponse = this.post("/auth", credentialRequest, null, SessionResponse.class);

        token = loginResponse.getData().getToken();

        SessionManager.storeToken(username, password, token);
        saveCookieInHeaders(token);
        System.out.println("Token saved");
    }

    private void saveCookieInHeaders(String token) {
        this.defaultHeaders.put("Cookie", buildCookie(token));
    }

    private String buildCookie(String token) {
        return "token=" + token;
    }

    private ResponseContainer buildResponse(Long endTime, Long startTime, Response response, Class responseClass) throws IOException {
        Long responseTime = endTime - startTime;

        ObjectMapper objectMapper = new ObjectMapper();
        int status = response.code();
        String headers = response.headers().toString();

        if (status < 200 || status >= 300) {
            return new ResponseContainer<>(null, status, headers, responseTime);
        }

        var data = objectMapper.readValue(response.body().string(), responseClass);
        return new ResponseContainer<>(data, status, headers, responseTime);
    }

    private String createTargetUrl(String url) {
        return baseUrl()+url;
    }

    public ResponseContainer getOne(String url, Map<String, String> headers, Class responseClass) throws IOException {
        if (headers == null) headers = defaultHeaders;

        Long startTime = new Date().getTime();
        Response response = apiClient.get(createTargetUrl(url), headers);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    public ResponseContainer post(String url, Object payload, Map<String, String> headers, Class responseClass) throws IOException {
        if (headers == null) headers = defaultHeaders;

        Long startTime = new Date().getTime();
        Response response = apiClient.post(createTargetUrl(url), payload, headers);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected ResponseContainer put(String url, Object payload, Map<String, String> headers, Class responseClass) throws IOException {
        if (headers == null) headers = defaultHeaders;

        Long startTime = new Date().getTime();
        Response response = apiClient.put(createTargetUrl(url), payload, headers);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

}
