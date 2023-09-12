package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UsersPayload;
import api.utilities.DataProviders;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

public class DataDrivenUserTest{

	UsersPayload userLoad;
	Faker fakeDb = new Faker();
	
	@Test(priority = 1, dataProvider = "DataUsers", dataProviderClass = DataProviders.class)
	public void testPostCreateUsers(String userId, String userName, String firstName, String lastName, String email,
			String password, String phone) {
		userLoad = new UsersPayload();
		userLoad.setId(Integer.parseInt(userId));
		userLoad.setUsername(userName);
		userLoad.setFirstname(firstName);
		userLoad.setLastname(lastName);
		userLoad.setEmail(email);
		userLoad.setPassword(password);
		userLoad.setPhone(phone);
		Response response = UserEndPoints.createUser(userLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
//		test.log(Status.PASS,String.valueOf(response.getStatusCode()));
//		test.log(Status.PASS,response.body().asString());

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUsers(String userName) {

		Response response = UserEndPoints.readUser(userName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
//		test.log(Status.PASS,String.valueOf(response.getStatusCode()));
//		test.log(Status.PASS,response.body().asString());

	}
	
	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testUpdateUser(String userName)
	{
		userLoad.setFirstname(fakeDb.name().firstName());
		userLoad.setLastname(fakeDb.name().lastName());
		userLoad.setEmail(fakeDb.internet().safeEmailAddress());
		
		Response responsebBefore = UserEndPoints.updateUser(userLoad, userName);
		responsebBefore.then().log().body();
		Assert.assertEquals(responsebBefore.getStatusCode(), 200);
//		test.log(Status.PASS, "Before Update :");
//		test.log(Status.PASS,String.valueOf(responsebBefore.getStatusCode()));
//		test.log(Status.PASS,responsebBefore.body().asString());
//		test.log(Status.PASS, "Before Update :");
		
		// * Verify updated data
		Response responseAfter = UserEndPoints.readUser(this.userLoad.getUsername());
		responseAfter.then().log().body();
		Assert.assertEquals(responseAfter.getStatusCode(), 200);
		Assert.assertEquals(responseAfter.jsonPath().get("email").toString(), this.userLoad.getEmail());
//		test.log(Status.PASS, "After Update :");
//		test.log(Status.PASS,String.valueOf(responseAfter.getStatusCode()));
//		test.log(Status.PASS,responseAfter.body().asString());
	}
	
	@Test(priority = 4, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserNames(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
//		test.log(Status.PASS,String.valueOf(response.getStatusCode()));
//		test.log(Status.PASS,response.body().asString());
	}

}
