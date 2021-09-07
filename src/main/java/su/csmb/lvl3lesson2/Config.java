package su.csmb.lvl3lesson2;

import su.csmb.lvl3lesson2.exception.LoadConfigrationIOFailed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class Config {

    private static final String CONFIGURATION_FILE = "./config/configuration.txt";
    private static String DB_TYPE;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public static String getDbType() {
        return DB_TYPE;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void loadConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(CONFIGURATION_FILE)));
        } catch (LoadConfigrationIOFailed e) {

        }

        DB_TYPE = properties.getProperty("DBType");
        URL = properties.getProperty("URL");
        USERNAME = properties.getProperty("USERNAME");
        PASSWORD = properties.getProperty("PASSWORD");
    }

}
