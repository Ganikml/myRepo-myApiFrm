package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.UsersPayload;

public class UserEndPoints2 {

	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;

	}

	public static Response createUser(UsersPayload payload) {

		String posturl = getUrl().getString("user_POST_url");

		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(posturl);

		return getResponse;
	}

	public static Response readUser(String username) {

		String geturl = getUrl().getString("user_GET_url");
		Response getResponse = given().pathParam("username", username).when().get(geturl);

		return getResponse;
	}

	public static Response updateUser(UsersPayload payload, String username) {
		String puturl = getUrl().getString("user_PUT_url");
		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(Routes.user_PUT_url);

		return getResponse;
	}

	public static Response deleteUser(String username) {
		String deleteurl = getUrl().getString("user_DELETE_url");
		Response getResponse = given().pathParam("username", username).when().delete(Routes.user_DELETE_url);

		return getResponse;
	}

}
