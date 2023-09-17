package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payload.UsersPayload;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

/**
 * USER - Model
 * 
 * @author Ganesh
 */
public class UserTest extends ExtentReportManager {

	Faker fakeDb;
	UsersPayload userLoad;
	public Logger logger;

	/**
	 * Payload Seup using POJO class
	 */
	@BeforeClass
	public void setupData() {
		fakeDb = new Faker();
		userLoad = new UsersPayload();

		userLoad.setId(fakeDb.idNumber().hashCode());
		userLoad.setUsername(fakeDb.name().username());
		userLoad.setFirstname(fakeDb.name().firstName());
		userLoad.setLastname(fakeDb.name().lastName());
		userLoad.setEmail(fakeDb.internet().safeEmailAddress());
		userLoad.setPassword(fakeDb.internet().password(8, 12));
		userLoad.setPhone(fakeDb.phoneNumber().cellPhone());

		// initiate logs
		logger = LogManager.getLogger(this.getClass());

	}

	/**
	 * POST- Create User
	 */
	@Test(priority = 1)
	public void testCreateUser() {
		logger.info("******** Create user **********");
		test.log(Status.INFO, "Create user");
		Response response = UserEndPoints2.createUser(userLoad);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("********user created **********");

	}

	/**
	 * GET- Read user
	 */
	@Test(priority = 2)
	public void testReadUser() {
		logger.info("******** Reading user information **********");
		test.log(Status.INFO, "Reading user information");
		Response response = UserEndPoints2.readUser(this.userLoad.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("******** User information is displayed **********");

	}

	/**
	 * PUT - Update user
	 */
	@Test(priority = 3)
	public void testUpdateUser() {
		test.log(Status.INFO, "Updating user information");
		logger.info("******** Updating user information **********");
		userLoad.setFirstname(fakeDb.name().firstName());
		userLoad.setLastname(fakeDb.name().lastName());
		userLoad.setEmail(fakeDb.internet().safeEmailAddress());

		Response responsebBefore = UserEndPoints2.updateUser(userLoad, this.userLoad.getUsername());
		responsebBefore.then().log().body();

		Assert.assertEquals(responsebBefore.getStatusCode(), 200);
		test.log(Status.INFO, "Before Updating User information");
		test.log(Status.PASS, "Status Code Validated " + responsebBefore.getStatusCode());
		test.log(Status.PASS, "Generated Response " + responsebBefore.getBody().asString());
		test.log(Status.INFO, "============================================================");

		// * Verify updated data
		Response responseAfter = UserEndPoints2.readUser(this.userLoad.getUsername());
		responseAfter.then().log().body();
		Assert.assertEquals(responseAfter.getStatusCode(), 200);
		Assert.assertEquals(responseAfter.jsonPath().get("email").toString(), this.userLoad.getEmail());
		test.log(Status.INFO, "After Updating User information");
		test.log(Status.PASS, "Status Code Validated " + responsebBefore.getStatusCode());
		test.log(Status.PASS, "Generated Response " + responsebBefore.getBody().asString());
		test.log(Status.INFO, "============================================================");
		logger.info("******** User updated **********");
	}

	/**
	 * DELETE - delete user
	 */
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("******** Deleting user information **********");
		test.log(Status.INFO, "Deleting user information");
		Response response = UserEndPoints2.deleteUser(this.userLoad.getUsername());
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
		test.log(Status.PASS, "Status Code Validated " + response.getStatusCode());
		test.log(Status.PASS, "Generated Response " + response.getBody().asString());
		logger.info("******** user information deleted **********");

	}

}
