package restassured.restassuredfirst;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restassured.restassuredfirst.utils.APIUtility;
import restassured.restassuredfirst.utils.DataUtility;

public abstract class BaseAPITest {

	RequestSpecification commonJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
			.setContentType(ContentType.JSON).build().log().all();
	RequestSpecification loginJsonSpec;

	@BeforeMethod
	public void login() {
		// String loginPayload =
		// "{\"email\":\"jogidemo321@gmail.com\",\"password\":\"builder123\"}";
		String loginPayload = DataUtility.getDataFromExcel("Payloads", "LoginPayload");
		System.out.println(loginPayload);
		Response response = given().spec(commonJsonSpec).body(loginPayload).when().post("/users/sign_in");

		// assertEquals(response.getStatusCode(), 200);
		APIUtility.verifyStatusCode(response);
		// String token = response.jsonPath().get("user.authtoken");
		String token = APIUtility.getBodyDataUsingJsonPath(response, "user.authtoken");
		loginJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
				.setContentType(ContentType.JSON).addHeader("authtoken", token).build().log().all();

	}
}
