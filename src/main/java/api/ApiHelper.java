package api;

import data.constants.EndPointsYandex;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Util;

import static io.restassured.RestAssured.given;


public class ApiHelper {

    public static Response getGetRequest(String endpoint) {
        Response response = given()
                //.relaxedHTTPSValidation()
                .accept(ContentType.ANY)
                //.cookie(String.valueOf(cookie))
                .contentType(ContentType.JSON)
                .get(endpoint);

        return response;
    }

    public static Response getGetRequestYandex(String endpoint) {
        Response response = given()
                .accept(ContentType.ANY)
                .header(EndPointsYandex.AUTHORIZATION, EndPointsYandex.AUTHORIZATION_TOKEN)
                .expect()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);

        return response;
    }

    public static Response getPostRequestYandex(String endpoint) {
        Response response = given()
                .accept(ContentType.ANY)
                .header(EndPointsYandex.AUTHORIZATION, EndPointsYandex.AUTHORIZATION_TOKEN)
                .log().all()
                .contentType(ContentType.JSON)
                .post(endpoint);

        return response;
    }
}
