package api.utils;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SchemaValidatorUtil {

    public static void validateSchema(Response response, String schemaPath){

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
