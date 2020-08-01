package com.sdet.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static String CONFIGFILE = "target\\test-classes\\config.properties";
    private static Properties properties;

    static {
        try {
            properties.load(new FileInputStream(new File(CONFIGFILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key)
    {
        return properties.get(key).toString();
    }

    public static int getInt(String key)
    {
        return Integer.parseInt(getString(key));
    }

    public static String getDecodedString(String key)
    {
        return String.valueOf(Base64.decodeBase64(getString(key).getBytes()));
    }
}
