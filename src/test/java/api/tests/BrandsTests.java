package api.tests;

import api.base.BaseTest;
import api.client.ApiClient;
import api.constants.Endpoints;
import api.models.request.Brands;
import api.models.request.Product;
import api.utils.PayloadBuilder.BrandsPayloadBuilder;
import api.utils.PayloadBuilder.ProductPayloadBuilder;
import api.utils.PojoConverter;
import api.utils.SchemaValidatorUtil;
import api.validators.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("AutomationExercise API")
@Feature("Brands")
public class BrandsTests extends BaseTest {

    private final  ApiClient apiClient = new ApiClient();

    @Test
    @Story("Get All Brands")
    @Description("Validate GET brands list returns 200, valid schema, and correct headers")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi Kumawat")
    public void getAllBrands(){

        Response response = apiClient.get(Endpoints.BRANDS_LIST);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateResponseTime(response, 5000);
        ResponseValidator.validateHeader(response, "Content-Type", "text/html; charset=utf-8");
        SchemaValidatorUtil.validateSchema(response, "schemas/brands-schema.json");

    }

    @Test
    @Story("POST Brands Not Supported")
    @Description("Validate POST to brands list returns 405 Method Not Allowed")
    @Severity(SeverityLevel.MINOR)
    @Owner("Prem Kumawat")
    public void postBrandsList(){

        Brands request = BrandsPayloadBuilder.brandsPayload();
        Map<String, String> formData = PojoConverter.brandsConvertToMap(request);

        Response response = apiClient.postForm(Endpoints.BRANDS_LIST, formData);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 405");
        ResponseValidator.validateHtmlBodyContains(response, "This request method is not supported.");

    }
}
