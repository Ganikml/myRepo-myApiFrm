package api.test;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Root;
import api.payload.Tags;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

/**
 * PET-Model
 * 
 * @author Ganesh
 */
public class PetTest extends ExtentReportManager {

	Faker fakeDb;
	Root petPayload;
	Category cg;
	Tags tg;
	public Logger logger;

	/**
	 * Payload Seup using POJO class
	 * 
	 */
	@BeforeClass
	public void setupData() {
		fakeDb = new Faker();
		ObjectMapper mapper = new ObjectMapper();
		Faker fk = new Faker();
		petPayload = new Root();
		cg = new Category();
		tg = new Tags();

		petPayload.setId(fk.idNumber().hashCode());
		cg.setId(fk.idNumber().hashCode());
		cg.setName(fk.name().name());
		petPayload.setCategory(cg);
		petPayload.setName(fk.dog().name());
		ArrayList<String> photoUrls = new ArrayList<String>();
		ArrayList<Tags> tags = new ArrayList<Tags>();
		photoUrls.add("C:\\Users\\Ganesh\\Desktop\\InstaUpload\\vn.jpeg");
		petPayload.setPhotoUrls(photoUrls);
		tg.setId(fk.idNumber().hashCode());
		tg.setName(fk.name().lastName());
		tags.add(tg);
		petPayload.setTags(tags);
		petPayload.setStatus("available");
		// initiate logs
		logger = LogManager.getLogger(this.getClass());

	}

	/**
	 * POST- Add new pet details
	 */
	@Test(priority = 1)
	public void testAddNewPet() {
		logger.info("******** Add New Pet **********");
		test.log(Status.INFO, "Add New Pet");
		Response response = PetEndPoints.addNewPet(petPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("******** New Pet Added Successfully **********");

	}

	/**
	 * GET- Read pet details
	 */
	@Test(priority = 2)
	public void testViewPetDetails() {
		logger.info("******** Add New Pet **********");
		Response response = PetEndPoints.readPetDetails(this.petPayload.getId());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("******** New Pet Added Successfully **********");
	}

	/**
	 * PUT- Update pet details
	 */
	@Test(priority = 3)
	public void testUpdatePetDetails() {
		test.log(Status.INFO, "Updating user information");
		logger.info("******** Updating user information **********");
		petPayload.setName("Duke");

		Response responsebBefore = PetEndPoints.updatePetDetails(petPayload);
		responsebBefore.then().log().body();

		Assert.assertEquals(responsebBefore.getStatusCode(), 200);
		test.log(Status.INFO, "Before Updating User information");
		test.log(Status.PASS, "Status Code Validated " + responsebBefore.getStatusCode());
		test.log(Status.PASS, "Generated Response " + responsebBefore.getBody().asString());
		test.log(Status.INFO, "============================================================");

		// * Verify updated data
		Response responseAfter = PetEndPoints.readPetDetails(this.petPayload.getId());
		responseAfter.then().log().body();
		Assert.assertEquals(responseAfter.getStatusCode(), 200);
		Assert.assertEquals(responseAfter.jsonPath().get("name").toString(), this.petPayload.getName());
		test.log(Status.INFO, "After Updating Pet information");
		test.log(Status.PASS, "Status Code Validated " + responsebBefore.getStatusCode());
		test.log(Status.PASS, "Generated Response " + responsebBefore.getBody().asString());
		test.log(Status.INFO, "============================================================");
		logger.info("******** Pet details updated **********");
	}

	/**
	 * DELETE- delete pet details
	 */
	@Test(priority = 4, enabled = true)
	public void testDeletePetDetails() {
		logger.info("******** Delete Pet Details **********");
		Response response = PetEndPoints.deletePet(this.petPayload.getId());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("******** Pet info deleted Successfully **********");
	}

}
