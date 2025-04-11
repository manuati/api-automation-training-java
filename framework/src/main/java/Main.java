import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import services.BookingService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please work...");

        BookingService bookingService = new BookingService();
        try {
            bookingService.authenticate();
            bookingService.authenticate();

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

            ResponseContainer<BookingResponse> responsePost = bookingService.addBooking(model, null);
            System.out.println("Made me a booking by POST: "+responsePost.getStatus().toString());
            // GET objeto unico
            var responseGetSingle = bookingService.getBooking(responsePost.getData().getBookingid(), null);
            System.out.println("Got me a booking by GET simple: "+responsePost.getStatus());

            var responseGetMany = bookingService.getBookings(null);

            System.out.println("Done. Dedico esta ejecucion sin issues a la hermosa de mi novia <3");
        } catch (Exception e) {
            System.out.println("Shit's fucked: "+e.getMessage());
            throw e;
        }
    }
}