package services;

import base.ServiceBase;
import models.requests.ObjectModel;
import models.responses.ObjectResponse;
import models.responses.ResponseContainer;

public class RestfulApiService extends ServiceBase {
    public RestfulApiService() {
        super("/objects");
    }

    public ResponseContainer<ObjectResponse> getObject(String objectId) {
        return this.getOne(this.url + "/" + objectId, null, ObjectResponse.class);
    }

    public ResponseContainer<ObjectResponse> createObject(ObjectModel objectModel) {
        return this.post(this.url, objectModel, null, ObjectResponse.class);
    }
}
