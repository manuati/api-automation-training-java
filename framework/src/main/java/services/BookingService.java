package services;

import base.ServiceBase;
import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;

import java.io.IOException;
import java.util.Map;

public class BookingService extends ServiceBase {
    public BookingService() {
        super("/booking");
    }

    public ResponseContainer<BookingResponse> addBooking(BookingModel model, Map<String, String> headers) {
        return this.post(this.url, model, headers, BookingResponse.class);
    }

    public ResponseContainer<BookingModel> getBooking(Long bookingId, Map<String, String> headers) throws IOException {
        return this.getOne(this.url + "/" + bookingId, headers, BookingModel.class);
    }

    public ResponseContainer<BookingModel> getBookings(Map<String, String> headers) {
        return this.getMany(this.url, headers, BookingModel.class);
    }
}
