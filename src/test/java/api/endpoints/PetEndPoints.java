package api.endpoints;

import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;
import api.payload.Root;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * API requests to process PET model urls
 * 
 * @author Ganesh
 */
public class PetEndPoints {

	/**
	 * Get Urls from property file using Resource Bundle
	 * 
	 * @return
	 */
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;

	}

	/**
	 * Add New Pet info
	 * 
	 * @param payload
	 * @return
	 */
	public static Response addNewPet(Root payload) {

		String posturl = getUrl().getString("pet_POST_url");

		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(posturl);

		return getResponse;
	}

	/**
	 * view pet details
	 * 
	 * @param id
	 * @return
	 */
	public static Response readPetDetails(int id) {

		String geturl = getUrl().getString("pet_Get_url");
		Response getResponse = given().pathParam("id", id).when().get(geturl);

		return getResponse;
	}

	/**
	 * Update pet detaials
	 * 
	 * @param payload
	 * @return
	 */
	public static Response updatePetDetails(Root payload) {
		String puturl = getUrl().getString("pet_PUT_url");
		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.put(puturl);

		return getResponse;
	}

	/**
	 * Delete pet details
	 * 
	 * @param id
	 * @return
	 */
	public static Response deletePet(int id) {
		String deleteurl = getUrl().getString("pet_DELETE_url");
		Response getResponse = given().pathParam("id", id).when().delete(deleteurl);

		return getResponse;
	}

}
