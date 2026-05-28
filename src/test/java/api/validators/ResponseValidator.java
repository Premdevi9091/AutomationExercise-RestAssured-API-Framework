package api.validators;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseValidator {

    //Validate status code
    public static void validateStatusCode(Response response, int expectedStatusCode){

        response.then()
                .statusCode(expectedStatusCode);

    }
    //Validate response time
    public static void validateResponseTime(Response response, long expectedTime){

        response.then()
                .time(lessThan(expectedTime));

    }
    //Validate response Header
    public static void validateHeader(Response response, String headerName, String expectedValue){

        response.then()
                .header(headerName, expectedValue);

    }
    //Validate response body value
    public static void validateBodyValue(Response response, String jsonPath, Object expectedValue){

        response.then()
                .body(jsonPath, equalTo(expectedValue));
    }

    // Validate HTML wrapped response body
    public static void validateHtmlBodyContains(Response response, String expectedValue) {

        String responseBody = response.htmlPath().getString("body");

        org.testng.Assert.assertTrue(responseBody.contains(expectedValue));
    }

}
