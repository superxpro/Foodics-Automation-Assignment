package foodicsTestCases;

import foodics.FoodicsApis;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    private String jwtToken; // variable to store the JWT token received from the login API
    private FoodicsApis foodicsApis; // object to store the login credentials

    // Test case to verify successful login with valid credentials
    @Test(priority=1,description = "Foodics Login API - Valid User Signin")
    @Description("When I Signin with an already signed up user, Then I should Signin successfully")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Test_case 1")
    @Issue("Software_bug")
    public void testLogin() throws IOException {

        // Create a JSON object with the login credentials
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("username", foodicsApis.getUsername());
        loginRequest.put("password", foodicsApis.getPassword());

        // Send a POST request to the login API with the correct credentials
        Response response = given().baseUri(foodicsApis.getBaseUrl())
                .header("Content-Type", "application/json")
                .body(loginRequest.toString())
                .when()
                .post(foodicsApis.getLOGIN_URL());


        // Assert that the status code of the response is 200 (OK)
        assertEquals(response.getStatusCode(), 200);

        // Parse the response body as a JSON object
        JsonPath loginJson = response.jsonPath();


        // Verify that the response body contains a JWT token
        String jwtToken = loginJson.getString("token");
        System.out.println(jwtToken);


        // Save the JWT token from the response
        saveJwtToken(jwtToken);
    }

    // Test case to verify successful retrieval of merchant info using the JWT token
    @Test(priority=2,description = "Foodics Get Merchant Info API - Get Request With JWT Token")
    @Description(" Verify successful retrieval of merchant info ")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case 2")
    @Issue("Software_bug")
    public void testGetMerchantInfo() {

        // Get the saved JWT token
        String jwtToken = getSavedJwtToken();

        // Send a GET request to the merchant info API with the JWT token
        Response response = given().baseUri(foodicsApis.getBaseUrl())
                .contentType(ContentType.JSON)
                .auth().oauth2(jwtToken)
                .when().get(foodicsApis.getMERCHANT_INFO_URL());

        // Verify the response status code
        assertEquals(200, response.getStatusCode());

        // Verify that the response body contains the expected merchant info
        String merchantName = response.jsonPath().getString("name");
        assert merchantName != null;
    }

    // Function to get the saved JWT token
    private String getSavedJwtToken() {
        // Get the saved JWT token from a variable
        return jwtToken;
    }

    private void saveJwtToken(String jwtToken) {
        // Save the JWT token in a variable for use in the next test case
        this.jwtToken = jwtToken;
    }

    @BeforeMethod
    public void methodSetup() throws IOException {
        // Initialize the FoodicsApis object
        foodicsApis = new FoodicsApis();
    }
}
