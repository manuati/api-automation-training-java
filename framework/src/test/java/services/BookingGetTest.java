package services;

import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.*;

import java.util.List;


public class BookingGetTest {
    private static Long bookingId;
    private static BookingService service;

    @BeforeAll
    public static void createBooking() {
        service = new BookingService();
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

        ResponseContainer<BookingResponse> response = new BookingService().addBooking(model, null);

        bookingId = response.getData().getBookingid();
    }

    @BeforeEach
    public void setup() {
        service.authenticate();
    }

    @Test
    @Tag("Unit")
    @Tag("Smoke")
    @DisplayName("Get Booking - Successful")
    public void getBookingSuccessful() {
        ResponseContainer<BookingModel> response = service.getBooking(bookingId, null);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData());
    }

    @Test
    @Tag("Unit")
    @DisplayName("Get Booking - Under 1000ms")
    public void getBookingSuccessfulLessThan1000ms() {
        ResponseContainer<BookingModel> response = service.getBooking(bookingId, null);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getResponseTime() < 1000);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Get All Bookings - Successful")
    public void getAllBookingsSuccessful() {
        ResponseContainer<List<BookingModel>> response = service.getBookings(null);

        Assertions.assertEquals(200, response.getStatus());
    }

}
