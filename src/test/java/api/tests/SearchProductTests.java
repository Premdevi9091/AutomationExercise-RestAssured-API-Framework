package api.tests;

import api.base.BaseTest;
import api.constants.Endpoints;
import api.models.request.SearchProductRequest;
import api.utils.PayloadBuilder.SearchProductsBuilder;
import api.utils.PojoConverter;
import api.validators.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.client.ApiClient;

import java.util.HashMap;
import java.util.Map;

@Epic("AutomationExercise API")
@Feature("Search Product")
public class SearchProductTests extends BaseTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    @Story("Search With Valid Parameter")
    @Description("Validate search product with a valid search_product parameter returns matching products")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi Kumawat")
    public void searchProduct() {

        SearchProductRequest request = SearchProductsBuilder.buildSearchProduct();
        Map<String, String> formData = PojoConverter.searchProductConvertToMap(request);

        Response response = apiClient.postForm(Endpoints.SEARCH_PRODUCT, formData);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 200");
        ResponseValidator.validateHtmlBodyContains(response, "products");

    }

    @Test
    @Story("Search Without Parameter")
    @Description("Validate search product without search_product parameter returns 400 Bad Request")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi Kumawat")
    public void searchProductWithoutParameter() {

        Map<String, String > formData = new HashMap<>();

        Response response = apiClient.postForm(Endpoints.SEARCH_PRODUCT, formData);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 400");
        ResponseValidator.validateHtmlBodyContains(response, "search_product parameter is missing");

    }
}
