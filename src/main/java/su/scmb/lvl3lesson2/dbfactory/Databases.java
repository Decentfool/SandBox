package su.scmb.lvl3lesson2.dbfactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface Databases<T extends Map> {
    void connectToDB();
    void createTableFromFile(String ddlFile);
    Connection getConnection();
    void insertRecord(String tableName, T value);
    void updateRecord(String tableName, T set, T where);
    List<String> selectRecord(String tableName, T select, T where);
}
