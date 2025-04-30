package services;

import models.requests.ObjectModel;
import models.responses.ObjectResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.*;

import java.util.HashMap;

public class RestfulApiServiceTest {

    private RestfulApiService service = new RestfulApiService();

    @Test
    public void getObjectSuccess() {
        ObjectModel objectModel = new ObjectModel();
        objectModel.setName("object-name");
        ResponseContainer<ObjectResponse> responsePost = service.createObject(objectModel);
        String objectId = responsePost.getData().getId();

        ResponseContainer<ObjectResponse> response = service.getObject(objectId);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(objectId, response.getData().getId());
    }

    @Test
    public void getObjectSuccessUnder1000ms() {
        ObjectModel objectModel = new ObjectModel();
        objectModel.setName("object-name");
        ResponseContainer<ObjectResponse> responsePost = service.createObject(objectModel);
        String objectId = responsePost.getData().getId();

        ResponseContainer<ObjectResponse> response = service.getObject(objectId);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getResponseTime() < 1000);
    }
    @Test
    public void createObjectSuccess() {
        HashMap<String, String> objectData = new HashMap<>();
        objectData.put("data1","value1");
        objectData.put("data2","value2");

        ObjectModel objectModel = new ObjectModel();
        objectModel.setName("object-name");
        objectModel.setData(objectData);
        ResponseContainer<ObjectResponse> response = service.createObject(objectModel);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData());

        ObjectResponse objectResponse = response.getData();

        Assertions.assertEquals(objectModel.getName(), objectResponse.getName());
        Assertions.assertNotNull(objectModel.getData());
        Assertions.assertNotNull(objectResponse.getId());
        Assertions.assertNotNull(objectResponse.getCreatedAt());
        Assertions.assertEquals(
                objectModel.getData().get("data1"),
                objectResponse.getData().get("data1")
        );
        Assertions.assertEquals(
                objectModel.getData().get("data2"),
                objectResponse.getData().get("data2")
        );
    }

}
