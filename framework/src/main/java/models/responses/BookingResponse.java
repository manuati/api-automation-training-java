package models.responses;

import models.requests.BookingModel;

public class BookingResponse {
    private Long bookingId;
    private BookingModel bookingModel;

    public BookingResponse() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BookingModel getBookingModel() {
        return bookingModel;
    }

    public void setBookingModel(BookingModel bookingModel) {
        this.bookingModel = bookingModel;
    }
}
