package api.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;

public class RequestSpecs {

    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification getFormRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(
                        "application/x-www-form-urlencoded"
                )
                .build();
    }
}
