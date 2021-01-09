package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

    static Properties properties;
    /**
     * This method read any given propertt file
     * @param filePath
     */

    public static Properties readProperties(String filePath){

        try{
            FileInputStream fis=new FileInputStream(filePath);
            Properties properties=new Properties();
            properties.load(fis);
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * This method retrieves singe value based on the specified key
     * @param key
     * @return
     */
    public static String getPropertyValue(String key){
        return properties.getProperty(key);
    }
}
