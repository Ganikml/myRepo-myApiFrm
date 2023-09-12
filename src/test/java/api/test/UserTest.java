package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UsersPayload;
import io.restassured.response.Response;

public class UserTest {

	Faker fakeDb;
	UsersPayload userLoad;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		fakeDb = new Faker();
		userLoad = new UsersPayload();
		
		userLoad.setId(fakeDb.idNumber().hashCode());
		userLoad.setUsername(fakeDb.name().username());
		userLoad.setFirstname(fakeDb.name().firstName());
		userLoad.setLastname(fakeDb.name().lastName());
		userLoad.setEmail(fakeDb.internet().safeEmailAddress());
		userLoad.setPassword(fakeDb.internet().password(8,12));
		userLoad.setPhone(fakeDb.phoneNumber().cellPhone());
		
		// initiate logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testCreateUser()
	{
		logger.info("******** Create user **********");
		Response response = UserEndPoints2.createUser(userLoad);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********user created **********");

	}
	
	@Test(priority = 2)
	public void testReadUser()
	{
		logger.info("******** Reading user information **********");
		Response response = UserEndPoints2.readUser(this.userLoad.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******** User information is displayed **********");

	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		logger.info("******** Updating user information **********");
		userLoad.setFirstname(fakeDb.name().firstName());
		userLoad.setLastname(fakeDb.name().lastName());
		userLoad.setEmail(fakeDb.internet().safeEmailAddress());
		
		Response responsebBefore = UserEndPoints2.updateUser(userLoad, this.userLoad.getUsername());
		responsebBefore.then().log().body();

		Assert.assertEquals(responsebBefore.getStatusCode(), 200);
		
		
		// * Verify updated data
		Response responseAfter = UserEndPoints2.readUser(this.userLoad.getUsername());
		responseAfter.then().log().body();
		Assert.assertEquals(responseAfter.getStatusCode(), 200);
		Assert.assertEquals(responseAfter.jsonPath().get("email").toString(), this.userLoad.getEmail());
		logger.info("******** User updated **********");
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		logger.info("******** Deleting user information **********");
		Response response = UserEndPoints2.deleteUser(this.userLoad.getUsername());
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******** user information deleted **********");

	}
	
	
}
