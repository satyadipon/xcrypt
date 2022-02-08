package com.secure.xcrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfXcrypt {
	private static Logger logger = LogManager.getLogger(ConfXcrypt.class);

	private static final Properties properties = new Properties();


	/* initialise data, the key defaults to this when none is specified at command line */
	static {
		initPropertiesFromFile();
	}

	private static void initPropertiesFromFile() {

		File file = new File("conf.properties");


		logger.info("User properties file path: {}", file.toPath());

		InputStream input = null;
		try {
			input = ConfXcrypt.class.getClassLoader().getResourceAsStream("conf.properties");

			if (input == null) {
				throw new Exception("Sorry, unable to find config.properties");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//load properties file
		try {
			properties.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String s) {

		if(s.contains("$"))
		{
			s=s.substring(1);
			return properties.getProperty(s);
		}
		else
			return s;
	}

}

