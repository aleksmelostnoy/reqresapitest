import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.Resources;
import utils.TestConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetRequests extends TestConfig {

	@Test
	public void verifySuccessResponse() {
		try {
			given()
			.when()
				.get(Resources.getUsersPage2EndPointUri())
			.then()
				.assertThat().statusCode(HttpStatus.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyResponseUserDataSchema() {
		try {
			UserData userData = given()
					.when()
						.get(Resources.getUser2EndPointUri())
					.then()
						.assertThat()
						.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/userdataschema.json")).and()
						.statusCode(HttpStatus.SC_OK).extract().body().as(UserData.class);
			Assert.assertEquals(userData.getData().getId(), 2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyResponseSchema() {
		try {
			given()
				.log().all()
			.when()
				.get(Resources.getUsersPage2EndPointUri())
			.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/schemaFile.jsd")).and()
				.statusCode(HttpStatus.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyAllTheFirstNameValuesFromResponsePayload() {
		try {
			given()
				.log().all()
			.when()
				.get(Resources.getUsersPage2EndPointUri())
			.then().assertThat()
				.statusCode(HttpStatus.SC_OK).log().all().and().contentType(ContentType.JSON).and()
				.body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyAllTheLasNameValuesFromResponsePayload() {
		try {
			given()
				.log().all()
			.when()
				.get(Resources.getUsersPage2EndPointUri())
			.then().assertThat()
				.statusCode(HttpStatus.SC_OK).log().all().and().contentType(ContentType.JSON).and()
				.body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

	@Test
	public void verifyResponsePayloadValues() {
		try {
			given()
				.log().all()
			.when()
				.get(Resources.getUsersPage2EndPointUri())
			.then().assertThat()
				.statusCode(HttpStatus.SC_OK).log().all().and().contentType(ContentType.JSON).and()
				.body("data[0].id", equalTo(7)).and().body("data[0].first_name", equalTo("Michael")).and()
				.body("data[0].last_name", equalTo("Lawson"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}

	}

	@Test
	public void verifyResponseHeaderValue() {
		try {
			Response res = given()
								.log().all()
							.when()
								.get(Resources.getUsersPage2EndPointUri())
							.then().assertThat()
								.header("X-Powered-By", "Express").log().all().extract().response();
			// Get all the headers
			Headers allHeaders = res.headers();
			for (Header header : allHeaders) {
				Reporter.log("Key: " + header.getName() + " Value: " + header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
		}
	}

}
