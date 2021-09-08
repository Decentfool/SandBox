package su.scmb.lvl3lesson2;

import su.scmb.lvl3lesson2.dbfactory.DatabaseFactory;
import su.scmb.lvl3lesson2.dbfactory.DatabaseTypes;
import su.scmb.lvl3lesson2.dbfactory.Databases;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        /*---- Loading Configuration ----*/
        Config.loadConfiguration();
        /*---- Loading Instance DatabaseFactory ----*/
        DatabaseFactory databaseFactory = DatabaseFactory.getInstance();

        Databases databasesConnection = databaseFactory.getConnection(DatabaseTypes.ORACLE);
        databasesConnection.connectToDB();
        databasesConnection.createTableFromFile(Paths.get("").toAbsolutePath() + Config.getSqlFilePath() + "/" + "createTable.sql");
        Map<String, String> employHashMap = new HashMap<>();
        /*TODO Correct order*/
        employHashMap.put("1", "INTEGER");
        employHashMap.put("IVAN", "VARCHAR");
        employHashMap.put("IVANOV", "VARCHAR");
        employHashMap.put("IVANOVICH", "VARCHAR");
        employHashMap.put("Directory", "VARCHAR");
        databasesConnection.insertRecord("EMPLOY", employHashMap);
        /*TODO Select, Update, Delete*/
    }
}
