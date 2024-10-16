package api;

import data.constants.YandexHeaders;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ApiHelper {

    public static Response getGetRequest(String endpoint) {
        Response response = given()
                .accept(ContentType.ANY)
                .expect()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);

        return response;
    }

    public static Response getGetRequestYandex(String endpoint) {
        Response response = given()
                .accept(ContentType.ANY)
                .header(YandexHeaders.AUTHORIZATION, YandexHeaders.AUTHORIZATION_TOKEN)
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
                .header(YandexHeaders.AUTHORIZATION, YandexHeaders.AUTHORIZATION_TOKEN)
                .log().all()
                .contentType(ContentType.JSON)
                .post(endpoint);

        return response;
    }

    public static Response getPutRequestYandex(String endpoint) {
        Response response = given()
                .accept(ContentType.ANY)
                .header(YandexHeaders.AUTHORIZATION, YandexHeaders.AUTHORIZATION_TOKEN)
                .log().all()
                .contentType(ContentType.JSON)
                .put(endpoint);

        return response;
    }
}
