package services;

import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.*;


public class BookingPostTest {

    private BookingService service = new BookingService();

    @BeforeEach
    public void setup() {
        service.authenticate();
    }

    @Test
    @Tag("Unit")
    @DisplayName("Create Booking - Successful")
    public void createBookingSuccessful() {
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

        ResponseContainer<BookingResponse> response = service.addBooking(model, null);

        BookingModel responseModel = response.getData().getBooking();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData().getBookingid());
        Assertions.assertEquals(model.getFirstname(), responseModel.getFirstname());
        Assertions.assertEquals(model.getLastname(), responseModel.getLastname());
        Assertions.assertEquals(model.getDepositpaid(), responseModel.getDepositpaid());
        Assertions.assertEquals(model.getTotalprice(), responseModel.getTotalprice());
        Assertions.assertEquals(model.getAdditionalneeds(), responseModel.getAdditionalneeds());
        Assertions.assertEquals(model.getBookingdates().getCheckin(), responseModel.getBookingdates().getCheckin());
        Assertions.assertEquals(model.getBookingdates().getCheckout(), responseModel.getBookingdates().getCheckout());
    }

}
