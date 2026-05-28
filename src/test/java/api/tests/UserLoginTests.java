package api.tests;

import api.base.BaseTest;
import api.client.ApiClient;
import api.constants.Endpoints;
import api.models.request.Users;
import api.utils.PojoConverter;
import api.utils.PayloadBuilder.UserPayloadBuilder;
import api.validators.ResponseValidator;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("AutomationExercise API")
@Feature("User Authentication")
public class UserLoginTests extends BaseTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    @Story("Valid Login")
    @Description("Verify login with valid credentials returns 200 and 'User exists!'")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Premdevi")
    public void validUserLogin(){

        // Step 1: Create user
        Users createdUser = UserPayloadBuilder.createUserPayload();
        apiClient.postForm(Endpoints.CREATE_ACCOUNT, PojoConverter.userConvertToMap(createdUser));

        // Step 2: Login
        Users request = UserPayloadBuilder.createEmailPasswordPayload(createdUser.getEmail(), createdUser.getPassword());

        Response response = apiClient.postForm(Endpoints.VERIFY_LOGIN, PojoConverter.userLoginConvertToMap(request));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "User exists!");

    }

    @Test
    @Story("Invalid Login")
    @Description("Verify login with invalid credentials returns responseCode 404 and 'User not found!'")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Premdevi")
    public void invalidUserLogin(){

        Users request = UserPayloadBuilder.createLoginInvalidPayload();
        Response response = apiClient.postForm(Endpoints.VERIFY_LOGIN, PojoConverter.userLoginConvertToMap(request));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 404");
        ResponseValidator.validateHtmlBodyContains(response, "User not found!");

    }

    @Test
    @Story("Login Missing Email")
    @Description("Verify login with only password returns responseCode 400 Bad Request")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Premdevi")
    public void loginWithPasswordOnly(){

        // Create a user first so the password is valid
        Users createdUser = UserPayloadBuilder.createUserPayload();
        apiClient.postForm(Endpoints.CREATE_ACCOUNT, PojoConverter.userConvertToMap(createdUser));


        Users request = UserPayloadBuilder.createLoginWithPasswordPayload(createdUser.getPassword());
        Response response = apiClient.postForm(Endpoints.VERIFY_LOGIN, PojoConverter.userLoginWithPasswordConvertToMap(request));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 400");
        ResponseValidator.validateHtmlBodyContains(response, "Bad request, email or password parameter is missing in POST request.");

    }

    @Test
    @Story("DELETE Method Not Allowed")
    @Description("Verify DELETE on verifyLogin returns responseCode 405 Method Not Supported")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi")

    public void deleteVerifyLogin() {

        Response response = apiClient.delete(Endpoints.VERIFY_LOGIN);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "\"responseCode\": 405");
        ResponseValidator.validateHtmlBodyContains(response, "This request method is not supported.");

    }
}
