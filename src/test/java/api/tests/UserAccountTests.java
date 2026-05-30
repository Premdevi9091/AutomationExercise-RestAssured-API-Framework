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
@Feature("User Account Management")
public class UserAccountTests extends BaseTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    @Story("Create Account")
    @Description("Verify user account creation with valid form data returns 200 and 'User created!'")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Premdevi Kumawat")
    public void createUserAccount(){

        Users createUser = UserPayloadBuilder.createUserPayload();
        Map<String, String> formData = PojoConverter.userConvertToMap(createUser);

        Response response = apiClient.postForm(Endpoints.CREATE_ACCOUNT, formData);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "User created!");

    }

    @Test
    @Story("Update Account")
    @Description("Verify user account update with valid data returns 200 and 'User updated!'")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Premdevi Kumawat")
    public void updateUserAccount(){

        // Step 1: Create user
        Users createdUser = UserPayloadBuilder.createUserPayload();
        apiClient.postForm(Endpoints.CREATE_ACCOUNT, PojoConverter.userConvertToMap(createdUser));

        // Step 2: Update user
        Users updatedUser = UserPayloadBuilder.createAccountUpdatePayload(createdUser);
        Response response = apiClient.put(Endpoints.UPDATE_ACCOUNT, PojoConverter.userConvertToMap(updatedUser));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "User updated!");

    }

    @Test
    @Story("Delete Account")
    @Description("Verify user account deletion with valid credentials returns 200 and 'Account deleted!'")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Premdevi Kumawat")
    public void deleteUserAccount(){

        // Step 1: Create user
        Users createdUser = UserPayloadBuilder.createUserPayload();
        apiClient.postForm(Endpoints.CREATE_ACCOUNT, PojoConverter.userConvertToMap(createdUser));

        // Step 2: Delete account
        Users request = UserPayloadBuilder.createEmailPasswordPayload(createdUser.getEmail(), createdUser.getPassword());

        Response response = apiClient.delete(Endpoints.DELETE_ACCOUNT, PojoConverter.userLoginConvertToMap(request));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, "Account deleted!");

    }

    @Test
    @Story("Get User Details By Email")
    @Description("Verify fetching user details by email returns 200 and the correct email in response")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Prem Kumawat")

    public void getUserDetailsByEmail(){

        // Step 1: Create user
        Users createdUser = UserPayloadBuilder.createUserPayload();
        apiClient.postForm(Endpoints.CREATE_ACCOUNT, PojoConverter.userConvertToMap(createdUser));

        // Step 2: Fetch user details
        Response response = apiClient.get(Endpoints.GET_USER_DETAIL, PojoConverter.userEmailConvertToMap(createdUser));

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateHtmlBodyContains(response, createdUser.getEmail());

    }
}
