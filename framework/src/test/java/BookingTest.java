import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.BookingService;

public class BookingTest {

    private BookingService service = new BookingService();

    @Test
    public void testCreateBooking() {
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

        service.authenticate();
        ResponseContainer<BookingResponse> response = service.addBooking(model, null);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData().getBookingid());
        Assertions.assertNotNull(response.getData().getBooking());

    }

}
