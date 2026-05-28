package api.client;

import api.utils.ApiReportUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.specifications.RequestSpecs;
import api.specifications.ResponseSpecs;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ApiClient {

    public Response get(String endpoint){

        Response response = given()
                .spec(RequestSpecs.getRequestSpec())
                .when()
                .get(endpoint)
                .then()
                .spec(ResponseSpecs.getResponseSpec())
                .extract()
                .response();

        ApiReportUtil.logApiTransaction("GET", endpoint, "Content-Type: application/json", null, response);
        return response;
    }

    public Response post(String endpoint, Object payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction(
                "POST",
                endpoint,
                "Content-Type: application/json",
                ApiReportUtil.formatBody(payload),
                response
        );
        return response;
    }

    public Response put(String endpoint, Map<String, String> formParams){

        Response response = given()
                .spec(RequestSpecs.getFormRequestSpec())
                .formParams(formParams)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction(
                "PUT",
                endpoint,
                "Content-Type: application/x-www-form-urlencoded",
                formParams.toString(),
                response
        );
        return response;
    }

    public Response delete(String endpoint){

        Response response = given()
                .spec(RequestSpecs.getFormRequestSpec())
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction("DELETE", endpoint, "Content-Type: application/json", null, response);
        return response;
    }

    public Response postForm(String endpoint, Map<String, String> formParams){

        Response response = given()
                .spec(RequestSpecs.getFormRequestSpec())
                .formParams(formParams)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction(
                "POST",
                endpoint,
                "Content-Type: application/x-www-form-urlencoded",
                formParams.toString(),
                response
        );
        return response;
    }

    public Response delete(String endpoint,Map<String, String> formParams){

        Response response = given()
                .spec(RequestSpecs.getFormRequestSpec())
                .formParams(formParams)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction(
                "DELETE",
                endpoint,
                "Content-Type: application/x-www-form-urlencoded",
                formParams.toString(),
                response
        );
        return response;
    }

    public Response get(String endpoint, Map<String, String> queryParams){

        Response response = given()
                .spec(RequestSpecs.getRequestSpec())
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        ApiReportUtil.logApiTransaction(
                "GET",
                endpoint,
                queryParams.toString(),
                "",
                response
        );

        return response;
    }
}
