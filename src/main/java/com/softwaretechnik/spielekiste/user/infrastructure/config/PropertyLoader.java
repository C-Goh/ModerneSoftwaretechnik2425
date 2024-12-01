package com.softwaretechnik.spielekiste.user.infrastructure.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    public static void loadProperties(String filePath) {
        final Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                System.setProperty(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}