package models.responses;

import org.json.JSONObject;

public class ResponseContainerJson {

    private JSONObject data;
    private String status;
    private JSONObject headers;
    private Long responseTime;

    public ResponseContainerJson(JSONObject data, String status, JSONObject headers, Long responseTime) {
        this.data = data;
        this.status = status;
        this.headers = headers;
        this.responseTime = responseTime;
    }

    public JSONObject getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public JSONObject getHeaders() {
        return headers;
    }

    public Long getResponseTime() {
        return responseTime;
    }
}
