package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class JsonUtil {

	public static XmlPath rawToXML(Response res) {
		String respon = res.asString();
		XmlPath xml = new XmlPath(respon);
		return xml;

	}

	public static JsonPath rawToJson(Response res) {
		String respon = res.asString();
		JsonPath json = new JsonPath(respon);
		return json;
	}

}
