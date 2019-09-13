package utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileReader {

	public String readFile(String fileName) throws IOException {
		ClassLoader loader = getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream(fileName);
		return IOUtils.toString(inputStream);
	}

}
