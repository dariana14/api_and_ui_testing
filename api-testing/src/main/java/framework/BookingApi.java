package framework;

import framework.request.Booking;
import framework.response.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi {

    private static final String API_URL = "https://restful-booker.herokuapp.com/booking/";

    public static Response getBookings(){
        return given().get(API_URL);
    }

    public static Response getBookingById(int bookingId){
        return getBookingById(bookingId, ContentType.JSON);
    }

    public static Response getBookingById(int bookingId, ContentType contentType){
        return given()
                .accept(contentType.toString())
                .when()
                .get(API_URL+ bookingId);
    }

    public static Response postBooking(Booking bookingPayload){
        return given()
                .contentType(ContentType.JSON.toString())
                .accept(ContentType.JSON.toString())
                .body(bookingPayload)
                .when()
                .post(API_URL);
    }

    public static Response updateBooking(BookingResponse booking, String token){
        return given()
                .accept(ContentType.JSON.toString())
                .contentType(ContentType.JSON.toString())
                .cookie("token", token)
                .body(booking.getBooking())
                .when()
                .put(API_URL + booking.getBookingid());
    }

    public static Response deleteBooking(BookingResponse booking, String token){
        return given()
                .accept(ContentType.JSON.toString())
                .cookie("token", token)
                .when()
                .delete(API_URL + booking.getBookingid());
    }
}
