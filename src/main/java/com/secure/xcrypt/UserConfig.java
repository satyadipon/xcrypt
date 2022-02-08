package com.secure.xcrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserConfig {
	private static Logger logger = LogManager.getLogger(UserConfig.class);

	private static final Properties properties = new Properties();
	private static final String environmentPrefix;


	/* initialise data, the key defaults to this when none is specified at command line */
	static {
		initPropertiesFromFile();
		environmentPrefix = System.getProperty("env", "key") + ".";	
	}

	private static void initPropertiesFromFile() {

		File file = new File("user.properties");


		logger.info("User properties file path: {}", file.toPath());

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//load properties file
		try {
			properties.load(fileInput);
			fileInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String s) {

		if(s.contains("$"))
		{
			s=s.substring(1);
			return properties.getProperty(environmentPrefix + s);
		}
		else
			return s;
	}

}

