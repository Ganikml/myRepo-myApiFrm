package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.UsersPayload;

/**
 * API requests to process USER model urls
 * 
 * @author Ganesh
 */
public class UserEndPoints {
	/**
	 * Create User
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createUser(UsersPayload payload) {
		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.user_POST_url);

		return getResponse;
	}

	/**
	 * read user details
	 * 
	 * @param username
	 * @return
	 */
	public static Response readUser(String username) {
		Response getResponse = given().pathParam("username", username).when().get(Routes.user_GET_url);

		return getResponse;
	}

	/**
	 * Update User details
	 * 
	 * @param payload
	 * @param username
	 * @return
	 */
	public static Response updateUser(UsersPayload payload, String username) {
		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(Routes.user_PUT_url);

		return getResponse;
	}

	/**
	 * Delete User details
	 * 
	 * @param username
	 * @return
	 */
	public static Response deleteUser(String username) {
		Response getResponse = given().pathParam("username", username).when().delete(Routes.user_DELETE_url);

		return getResponse;
	}

}
