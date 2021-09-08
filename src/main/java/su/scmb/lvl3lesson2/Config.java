package su.scmb.lvl3lesson2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public final class Config {
    private static final Logger logger = Logger.getLogger(Config.class.getName());
    private static final String CONFIGURATION_FILE = "/config/db_configuration.cfg";
    private static String DB_TYPE;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String SQL_FILE_PATH;

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

    public static String getSqlFilePath() {
        return SQL_FILE_PATH;
    }

    public static void loadConfiguration(){
        final Properties properties = new Properties();
        Path currentAbsolutePath = Paths.get("");
        try {
            properties.load(new FileInputStream(new File(currentAbsolutePath.toAbsolutePath() + CONFIGURATION_FILE)));
        } catch (IOException e) {
            logger.severe("Error while load configuration");
            e.printStackTrace();
            System.gc();
        }

        DB_TYPE = properties.getProperty("DBType");
        URL = properties.getProperty("URL");
        USERNAME = properties.getProperty("Username");
        PASSWORD = properties.getProperty("Password");
        SQL_FILE_PATH = properties.getProperty("pathToSQLFile");
    }

}
