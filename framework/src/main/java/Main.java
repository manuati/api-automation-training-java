import base.ServiceBase;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            model.setFirstName("Jim");
            model.setLastName("Brown");
            model.setDepositPaid(true);
            model.setTotalPrice(111);
            model.setAdditionalNeeds("Breakfast");
            BookingModel.BookingDates bookingDates = new BookingModel.BookingDates();
            bookingDates.setCheckIn("2018-01-01");
            bookingDates.setCheckOut("2019-01-01");
            model.setBookingDates(bookingDates);

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseContainer response = serviceBase.post("https://restful-booker.herokuapp.com/booking", objectMapper.writeValueAsString(model), null, BookingResponse.class);
            System.out.println("Got me a response");
        } catch (Exception e) {
            System.out.println("Shit's fucked: "+e.getMessage());
            throw e;
        }
    }
}