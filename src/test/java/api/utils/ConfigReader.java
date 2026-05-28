package api.utils;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try(InputStream in = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")){

            if(in == null){
                throw new RuntimeException("config/config.properties not found on classpath");
            }
            props.load(in);

        } catch (IOException e){
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key){
        String value = props.getProperty(key);
        if(value == null){
            throw new RuntimeException("Missing config property: " + key);
        }
        return value;
    }

    public static long getLong(String key){
        return Long.parseLong(get(key));
    }
}
