package api.tests;

import api.base.BaseTest;
import api.client.ApiClient;

import api.constants.Endpoints;
import api.models.request.Product;
import api.utils.PojoConverter;
import api.utils.PayloadBuilder.ProductPayloadBuilder;
import api.utils.SchemaValidatorUtil;
import api.validators.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("AutomationExercise API")
@Feature("Products")
public class ProductTests extends BaseTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    @Story("Get All Products")
    @Description("Validate GET products list returns 200, valid schema, and correct Content-Type header")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi")
    public void getAllProducts(){

        Response response = apiClient.get(Endpoints.PRODUCTS_LIST);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateResponseTime(response, 5000);
        ResponseValidator.validateHeader(response, "Content-Type", "text/html; charset=utf-8");
        SchemaValidatorUtil.validateSchema(response, "schemas/products-schema.json");

    }

    @Test
    @Story("POST Products Not Supported")
    @Description("Validate POST to products list returns 405 Method Not Allowed")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi")
    public void postProductList(){

        Product request = ProductPayloadBuilder.buildProduct();
        Map<String, String> formData = PojoConverter.productConvertToMap(request);

        Response response = apiClient.postForm(Endpoints.PRODUCTS_LIST, formData);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 405");
        ResponseValidator.validateHtmlBodyContains(response, "This request method is not supported.");
    }
}
