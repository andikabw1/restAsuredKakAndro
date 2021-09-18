package restassured.restassuredfirst.utils;

import io.restassured.response.Response;

public class APIUtility {
	public static boolean verifyStatusCode(Response response) {
		return response.getStatusCode() == 200;
	}

	public static <T> T getBodyDataUsingJsonPath(Response response, String jsonPath) {
		return response.jsonPath().get(jsonPath);
	}

}
