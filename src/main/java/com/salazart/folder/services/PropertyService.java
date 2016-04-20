package com.salazart.folder.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyService {
	private final static String PATH_PROPERTIES = "src/main/resources/config.properties";
	public static Logger log = LogManager.getRootLogger();
	
	public static void setValueProperties(String typeProperties, String valueProperties) {
		Properties prop = new PropertyService().getProperties();
		prop.setProperty(typeProperties, valueProperties);
		OutputStream out = null;
		try {
			out = new FileOutputStream(PATH_PROPERTIES);
			prop.store(out, null);
		} catch (IOException e) {
			log.error(e + " Error read properties from file: "
					+ PATH_PROPERTIES);
			if (createPath(PATH_PROPERTIES)) {
				setValueProperties(typeProperties, valueProperties);
			}
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage() + " Error read properties");
			}
		}
	}

	private static boolean createPath(String path) {
		File f = new File(PATH_PROPERTIES);
		f.getParentFile().mkdirs();
		try {
			f.createNewFile();
			log.info("File: " + PATH_PROPERTIES
					+ " created successfully");
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage() + " Can't create file: " + path);
			return false;
		}
	}

	public static Properties getProperties() {
		Properties prop = new Properties();
		if (new File(PATH_PROPERTIES).isFile()) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(PATH_PROPERTIES);
				prop.load(inputStream);
				inputStream.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Don't found propertyfile " + PATH_PROPERTIES);
		}
		return prop;
	}

	public static String getValueProperties(String typeProperties) {
		Properties prop = new PropertyService().getProperties();
		String valueProperties = prop.getProperty(typeProperties);
		if (valueProperties != null) {
			return valueProperties;
		} else {
			setValueProperties(typeProperties, "");
			return "";
		}
	}
}
