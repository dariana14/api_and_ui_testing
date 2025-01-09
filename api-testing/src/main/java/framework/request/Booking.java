package framework.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@Builder
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public static Booking getFullPayload(){
        Faker faker = new Faker();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(faker.date().future(1, TimeUnit.DAYS));
        bookingDates.setCheckout(faker.date().future(3, TimeUnit.DAYS));

        return Booking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDigit())
                .depositpaid(true)
                .additionalneeds(faker.howIMetYourMother().catchPhrase())
                .bookingdates(bookingDates)
                .build();
    }
}
