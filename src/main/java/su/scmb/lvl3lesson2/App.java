package su.scmb.lvl3lesson2;

import su.scmb.lvl3lesson2.dbfactory.DatabaseFactory;
import su.scmb.lvl3lesson2.dbfactory.DatabaseTypes;
import su.scmb.lvl3lesson2.dbfactory.Databases;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        /*---- Loading Configuration ----*/
        Config.loadConfiguration();
        /*---- Loading Instance DatabaseFactory ----*/
        DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
        /*---- Connect to db -----*/
        Databases databasesConnection = databaseFactory.getConnection(DatabaseTypes.ORACLE);
        databasesConnection.connectToDB();
        /*---- Create table ----*/
        databasesConnection.createTableFromFile(Paths.get("").toAbsolutePath() + Config.getSqlFilePath() + "/" + "createTable.sql");
        /*---- Insert Records ----*/
        Map<String, String> employTreeMap = new TreeMap<>();
        employTreeMap.put("ID", "1");
        employTreeMap.put("FIRST_NAME","IVAN");
        employTreeMap.put("LAST_NAME", "IVANOV");
        employTreeMap.put("MIDDLE_NAME", "IVANOVICH");
        employTreeMap.put("DEPARTMENT", "Directory");
        databasesConnection.insertRecord("EMPLOY", employTreeMap);
        employTreeMap.clear();
        employTreeMap.put("ID", "2");
        employTreeMap.put("FIRST_NAME","PETR");
        employTreeMap.put("LAST_NAME", "PETROV");
        employTreeMap.put("MIDDLE_NAME", "PETROVICH");
        employTreeMap.put("DEPARTMENT", "Worker");
        databasesConnection.insertRecord("EMPLOY", employTreeMap);
        /*---- Select after Insert ----*/
        TreeMap<String, String> employSelect = new TreeMap<>();
        TreeMap<String, String> employWhereInSelect = new TreeMap<>();
        /*TODO to list*/
        employSelect.put("FIRST_NAME", "-");
        employSelect.put("MIDDLE_NAME", "-");
        employSelect.put("DEPARTMENT", "-");
        employWhereInSelect.put("ID", "2");
        List<String> record  = databasesConnection.selectRecord("EMPLOY",employSelect, employWhereInSelect);
        record.forEach(LOGGER::info);
        record.clear();
        employSelect.clear();
        employWhereInSelect.clear();
        /*---- Update Records ----*/
        Map<String, String> employSet = new TreeMap<>();
        Map<String, String> employWhere = new TreeMap<>();
        // Wrong Where clause
        employSet.put("FIRST_NAME", "SERGEY");
        employWhere.put("ID", "1");
        employWhere.put("LAST_NAME", "SIDOROV");
        databasesConnection.updateRecord("EMPLOY",employSet, employWhere);
        employSet.clear();
        employWhere.clear();
        // Right Where clause
        employSet.put("FIRST_NAME", "SERGEY");
        employSet.put("LAST_NAME", "SERGEEV");
        employWhere.put("ID", "2");
        employWhere.put("LAST_NAME", "PETROV");
        databasesConnection.updateRecord("EMPLOY",employSet, employWhere);
        /*---- Select after Update ----*/
        /*TODO to list*/
        employSelect.put("FIRST_NAME", "-");
        employSelect.put("MIDDLE_NAME", "-");
        employSelect.put("DEPARTMENT", "-");
        employWhereInSelect.put("ID", "2");
        record  = databasesConnection.selectRecord("EMPLOY",employSelect, employWhereInSelect);
        record.forEach(LOGGER::info);
        /*TODO Delete*/
    }
}
