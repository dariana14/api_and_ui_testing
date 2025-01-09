package framework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi {

    public static final String API_URL = "https://restful-booker.herokuapp.com/auth";

    public static final String VALID_AUTHENTICATION_DATA = """
                {
                "username" : "admin",
                "password" : "password123"
                }
                """;

    public static Response authentication(String responseBody){
        return given()
                .accept(ContentType.JSON.toString())
                .contentType(ContentType.JSON)
                .body(responseBody)
                .when()
                .post(API_URL);
    }

    public static Response validAuthentication(){
        return given()
                .accept(ContentType.JSON.toString())
                .contentType(ContentType.JSON)
                .body(VALID_AUTHENTICATION_DATA)
                .when()
                .post(API_URL);
    }

    public static String getToken(){
        return validAuthentication()
                .then()
                .extract()
                .path("token");
    }
}
