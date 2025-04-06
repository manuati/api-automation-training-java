package base;

import models.requests.CredentialModel;
import models.responses.ResponseContainer;
import models.responses.ResponseContainerJson;
import okhttp3.Response;
import org.json.JSONObject;
import utils.ErrorMessages;
import utils.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceBase<T> {

    protected ApiClient apiClient;
    protected String url;
    // TODO: Agregar instancia de configuracion default (Un mapa, imagino). Revisar bien como se usa en el framework original
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

    private static String authUrl() {
        return baseUrl() + "/auth";
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

        // Verifico si hay un token cacheado. Si lo hay, lo seteo dentro de la configuracion, en los headers (cookie de token=<token cacheado>) y retorno
        String token = SessionManager.getCachedToken(username, password);
        if (!StringUtils.isEmpty(token)) {
            saveCookieInHeaders(token);
            System.out.println("Token refreshed");
            return;
        }

        CredentialModel requestBody = new CredentialModel(username, password);
        ResponseContainerJson response = this.post(authUrl(), new JSONObject(requestBody));

        token = response.getData().getString("token");
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

    private void buildResponse(Integer endTime, Integer startTime, T response) {
        // Calculo el tiempo de la respuesta como endTime - startTime
        // Creo un obejto Response tipado que posee la data del objeto, el status de la respuesta, los headers de la respuesta, y el tiempo de la respuesta
    }

    protected T get(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }

    protected ResponseContainerJson post(String url, JSONObject payload) throws IOException {
        return post(url, payload, defaultHeaders);
    }

    protected ResponseContainerJson post(String url, JSONObject payload, Map<String, String> headers) throws IOException {
        Long startTime = new Date().getTime();
        Response response = apiClient.post(url, payload, headers);
        Long endTime = new Date().getTime();

        return new ResponseContainerJson(new JSONObject(response.body().string()),
                response.header("Status"),
                new JSONObject(response.headers().toMultimap()),
                endTime-startTime);
    }

    protected T put(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }

    protected T patch(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }

    protected T delete(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }

    protected T head(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }

    protected T options(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
    }
}
