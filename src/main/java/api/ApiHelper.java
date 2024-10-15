package org.example.api;

import io.restassured.response.Response;

public class ApiHelper {

    public static Response getGetRequest(String cookie, String endpoint) {
        Response response = given()
                .relaxedHTTPSValidation()
                .accept(ContentType.ANY)
                .cookie(String.valueOf(cookie))
                .contentType(ContentType.JSON)
                .get(endpoint);
        LogHelper.response(response.getBody().asString());
        return response;
    }
}
