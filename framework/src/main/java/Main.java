import base.ServiceBase;
import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please work...");

        ServiceBase serviceBase = new ServiceBase("");
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

            ResponseContainer<BookingResponse> responsePost = serviceBase.post("https://restful-booker.herokuapp.com/booking", model, null, BookingResponse.class);
            System.out.println("Made me a booking by POST: "+responsePost.getStatus().toString());
            // GET objeto unico
            var responseGetSingle = serviceBase.getOne("https://restful-booker.herokuapp.com/booking/"+responsePost.getData().getBookingid(), null, BookingModel.class);
            System.out.println("Got me a booking by GET simple: "+responsePost.getStatus());

            System.out.println("Done. Dedico esta ejecucion sin issues a la hermosa de mi novia <3");
        } catch (Exception e) {
            System.out.println("Shit's fucked: "+e.getMessage());
            throw e;
        }
    }
}