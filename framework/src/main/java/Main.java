import base.ServiceBase;
import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Why are you running me, bro?");

        ServiceBase<String> serviceBase = new ServiceBase<>("");
        try {
            serviceBase.authenticate();
            serviceBase.authenticate();

            BookingModel model = new BookingModel();
            model.setFirstname("Jim");
            model.setLastname("Brown");
            model.setDepositpaid(true);
            model.setTotalprice(111);
            model.setAdditionalneeds("Breakfast");
            BookingModel.BookingDates bookingDates = new BookingModel.BookingDates();
            bookingDates.setCheckin("2018-01-01");
            bookingDates.setCheckout("2019-01-01");
            model.setBookingdates(bookingDates);

            ResponseContainer<BookingResponse> response = serviceBase.post("https://restful-booker.herokuapp.com/booking", model, null, BookingResponse.class);

            //var response = serviceBase.get("https://restful-booker.herokuapp.com/booking", objectMapper);
            System.out.println("Got me a response for POST: "+response.getStatus());
        } catch (Exception e) {
            System.out.println("Shit's fucked: "+e.getMessage());
            throw e;
        }
    }
}