package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import api.payload.UsersPayload;

public class UserEndPoints {
	
	public static Response createUser(UsersPayload payload)
	{
		Response getResponse = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.user_POST_url);
		
		return getResponse;
	}
	public static Response readUser(String username)
	{
		Response getResponse = given()
				.pathParam("username", username)
				.when()
				.get(Routes.user_GET_url);
				
				return getResponse;
	}
	
	public static Response updateUser(UsersPayload payload, String username)
	{
		Response getResponse = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)
		.when()
		.put(Routes.user_PUT_url);
		
		return getResponse;
	}
	
	public static Response deleteUser(String username)
	{
		Response getResponse = given()
				.pathParam("username", username)
				.when()
				.delete (Routes.user_DELETE_url);
				
				return getResponse;
	}

}
