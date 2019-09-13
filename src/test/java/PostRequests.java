import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.JsonUtil;
import utils.Resources;
import utils.TestConfig;

import static io.restassured.RestAssured.given;

public class PostRequests extends TestConfig {

	@Test
	public void verifyUsersRegistration() {
		try {
			given()
				.header("Content-Type", "application/json")
				.body(userPersonalDetailsPayload)
				.log().all()
			.when()
				.post(Resources.getRegisterEndPointUri())
			.then()
				.statusCode(HttpStatus.SC_OK).log().all();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyUsersEntryAndResponseconsistId() {
		try {
			Response res = given()
								.header("Content-Type", "application/json")
								.body(userDetailsPayload)
							.when()
								.log().all()
								.post(Resources.getUserEntryEndPointUri())
							.then()
								.statusCode(HttpStatus.SC_CREATED)
								.log().all()
								.extract().response();
			JsonPath js = JsonUtil.rawToJson(res);
			String id = js.get("id");
			Assert.assertEquals(id.length(), 3,"id length is not 3 digits");
			Reporter.log("id generated");
		} catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
	    }
	}

}
