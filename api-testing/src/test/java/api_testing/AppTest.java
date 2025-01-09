package api_testing;

import framework.AuthApi;
import framework.BookingApi;
import framework.request.Booking;
import framework.response.BookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class AppTest {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking/";

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get(API_URL)
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void whenPostAuth_thenShouldReturnHttpStatus200() {

        AuthApi.validAuthentication().then().statusCode(SC_OK);
    }

    @Test
    public void whenPostAuth_thenShouldReturnToken2() {
        AuthApi.validAuthentication()
                .then()
                .body("token" , notNullValue());
    }

    @Test
    public void whenPostAuthIncorrectPassword_thenShouldReturnBadCredentials() {
        String body = """
                {
                "username" : "admin",
                "password" : "password124"
                }
                """;

        AuthApi.authentication(body)
                .then()
                .body("reason" , equalTo("Bad credentials"));
    }

    @Test
    public void givenWrongAcceptType_whenGetBooking_thenShouldReturnHttpStatus418() {
        BookingApi.getBookingById(10, ContentType.fromContentType("text/plain"))
                .then()
                .statusCode(418);
    }

    @Test
    public void whenPutBooking_thenShouldReturnHttpStatus200() {

        String token = AuthApi.getToken();

        Booking bookingPayload = Booking.getFullPayload();

        BookingResponse bookingResponse = BookingApi.postBooking(bookingPayload)
                .as(BookingResponse.class);

        BookingApi.updateBooking(bookingResponse, token)
                .then()
                .statusCode(200);

    }

    @Test
    public void whenDeleteBooking_thenShouldReturnHttpStatus201() {

        String token = AuthApi.getToken();

        Booking bookingPayload = Booking.getFullPayload();

        BookingResponse bookingResponse = BookingApi.postBooking(bookingPayload)
                .as(BookingResponse.class);

        BookingApi.deleteBooking(bookingResponse, token)
                .then()
                .statusCode(201);
    }
}
