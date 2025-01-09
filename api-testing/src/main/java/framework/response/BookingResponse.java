package framework.response;

import framework.request.Booking;
import lombok.Data;

@Data
public class BookingResponse {
    private int bookingid;
    private Booking booking;
}
