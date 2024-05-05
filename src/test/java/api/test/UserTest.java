package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	Faker fakename;
	User userPayload;

	@BeforeClass
	public void SetUpDataI() {

		fakename = new Faker();
		userPayload = new User();

		userPayload.setId(fakename.idNumber().hashCode());
		userPayload.setUsername(fakename.name().username());
		
		userPayload.setFirstname(fakename.name().firstName());
		userPayload.setLastname(fakename.name().lastName());
		userPayload.setEmail(fakename.internet().emailAddress());
		userPayload.setPassword(fakename.internet().password());
		userPayload.setPhone(fakename.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void TestCreateUser() {

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void TestReadUser() {

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void TestUpdateUser() {
		
		userPayload.setFirstname(fakename.name().firstName());
		userPayload.setLastname(fakename.name().lastName());
		userPayload.setEmail(fakename.internet().emailAddress());

		Response response = UserEndPoints.UpdateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void TestDeleteUser() {

		Response response = UserEndPoints.DeleteUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
