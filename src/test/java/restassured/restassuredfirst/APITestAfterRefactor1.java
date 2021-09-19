package restassured.restassuredfirst;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import restassured.restassuredfirst.utils.APIUtility;

/**
 * Unit test for simple App.
 */
public class APITestAfterRefactor1 extends BaseAPITest {
	/**
	 * Rigorous Test :-)
	 */
	String token = "";

	@Test
	public void dashboardAPI() {

		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get("/build_cards");
		APIUtility.verifyStatusCode(response);

	}

	@Test
	public void configurationsAPI() {

		Response response = given().spec(loginJsonSpec).when().get("/configurations");
		APIUtility.verifyStatusCode(response);
		AssertJUnit.assertEquals(APIUtility.getBodyDataUsingJsonPath(response, "currency.data.type"), "currency");

	}

	@Test
	public void fakerTest() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
	}

	@Test
	public void fakerTest2() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
	}

}
