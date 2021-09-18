package restassured.restassuredfirst;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class APITest {
	/**
	 * Rigorous Test :-)
	 */
	String token = "";

	@Test(priority = 1)
	public void testLoginAPI() {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		String loginPayload = "{\"email\":\"jogidemo321@gmail.com\",\"password\":\"builder123\"}";
		Response response = RestAssured.given().contentType("application/json").body(loginPayload).when()
				.post("/users/sign_in");

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		token = response.jsonPath().get("user.authtoken");
	}

	@Test(priority = 2)
	public void dashboardAPI() {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";

		Response response = RestAssured.given().contentType("application/json").header("authtoken", token)
				.param("status", "completed").when().get("/build_cards");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test
	public void fakerTest() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
	}
}
