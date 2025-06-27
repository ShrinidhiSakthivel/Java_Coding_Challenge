package com.hexaware.hms.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties getPropertyString(String fileName) {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(fileName)) {
            props.load(reader);
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
        }
        return props;
    }
}
