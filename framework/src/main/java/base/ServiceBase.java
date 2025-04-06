package base;

import utils.ErrorMessages;
import utils.StringUtils;

public class ServiceBase<T> {

    protected ApiClient apiClient;
    protected String url;
    // TODO: Agregar instancia de configuracion default (Un mapa, imagino). Revisar bien como se usa en el framework original

    public ServiceBase(String endpointPath) {
        this.apiClient = ApiClient.getInstace();
        this.url = baseUrl() + endpointPath;
    }

    private static String baseUrl() {
        String url = System.getenv("BASE_URL");
        if (url == null) url = "";

        return url;
    }

    /**
     * Authenticates the user registered in the env vars to make calls against the API
     */
    public final void authenticate() throws RuntimeException {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException(ErrorMessages.MISSING_USERNAME_PASSWORD);
        }

        // Verifico si hay un token cacheado. Si lo hay, lo seteo dentro de la configuracion, en los headers (cookie de token=<token cacheado>) y retorno
        String token = SessionManager.getCachedToken(username, password);
        if (!StringUtils.isEmpty(token)) {

            return;
        }


        // Realizo un login contra el servicio de authorization haciendo uso de las credenciales
        // Almaceno el token dentro del SessionManager
        // Seteo el token dentro de la configuracion, en los headers
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

    protected T post(String url) {
        // Guardo el tiempo actual como comienzo
        // Invoco el servicio GET en el 'url' usando el api client
        // Guardo el tiempo actual como fin
        // Construyo un objeto de respuesta haciendo uso de la respuesta de la api y los tiempos de inicio y fin
        // Retorno el objeto creado
        return null;
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
