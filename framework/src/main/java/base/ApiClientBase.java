package base;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiClientBase {

    // Clase base del api client. En la version de typescript. La misma posee el cliente que hace las invocaciones a la URL
    // Esta clase deberia construir y poner a disposicion el client a utilizar para las invocaciones HTTP
    // Opciones de librerias: Jersey, OkHttp, Apache HttpClient, Java 11 HttpClient
    private Map<String, String> defaultHeaders;

    protected ApiClientBase() {
        defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json");
        defaultHeaders.put("Accept","application/json");
    }

    public void setToken(String token) {
        StringBuilder builder = new StringBuilder();
        builder.append("token=");
        builder.append(token);

        defaultHeaders.put("Cookie", builder.toString());
    }

    public void getClient(){
        // Metodo que retorna el cliente a usar
    }
}
