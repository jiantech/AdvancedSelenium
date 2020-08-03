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
            properties = new Properties();
            properties.load(new FileInputStream(new File(CONFIGFILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key)
    {
        System.out.println(properties.get(key).toString());
        return properties.get(key).toString();
    }

    public static int getInt(String key)
    {
        return Integer.parseInt(getString(key));
    }

    public static String getDecodedString(String key)
    {
        Base64 base64 = new Base64();
        return new String(base64.decode(getString(key).getBytes()));
    }
}
