package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
	protected String userDetailsPayload;
	protected String userPersonalDetailsPayload;
	Properties prop;

	@BeforeMethod
	public void preCondition() throws IOException {
		userDetailsPayload = new FileReader().readFile("requests/userDetails.json");
		userPersonalDetailsPayload = new FileReader().readFile("requests/userPersonalDetails.json");
		FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
		prop = new Properties();
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("HOST");
	}

}
