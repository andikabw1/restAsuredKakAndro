package restassured.restassuredfirst;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertNotEquals;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import restassured.restassured.apis.APIEndpoints;
import restassured.restassured.apis.JsonPaths;
import restassured.restassuredfirst.utils.APIUtility;
import restassured.restassuredfirst.utils.DataUtility;

/**
 * Unit test for simple App.
 */
public class APITestAfterRefactor2 extends BaseAPITest {
	/**
	 * Rigorous Test :-)
	 */
	String token = "";

	@Test
	public void dashboardAPI() {

		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
		System.out.println(response.getBody().asPrettyString());
		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));
	}

	@Test
	public void configurationsAPI() {

		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.configurations);
		APIUtility.verifyStatusCode(response);
		AssertJUnit.assertEquals(APIUtility.getBodyDataUsingJsonPath(response, JsonPaths.currencyType), "currency");
		System.out.println(APIUtility.getBodyDataUsingJsonPath(response, "currency.data.attributes.code"));
	}

	@Test
	public void fakerTest() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
	}

	@Test
	public void testInvalidCredentials() {

		String loginPayload = DataUtility.getDataFromExcel("Payloads", "IncorrectLoginPayload")
				.replace("$.username", "a@gmail.com").replace("$.password", "a@1233323232");

		Response response = given().spec(commonJsonSpec).body(loginPayload).when().post(APIEndpoints.login);
		assertNotEquals(response.getStatusCode(), 200);
	}
}
