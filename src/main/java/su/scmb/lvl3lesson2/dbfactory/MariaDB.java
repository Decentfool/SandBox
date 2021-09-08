package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.exception.InvalidFieldType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class MariaDB<T extends Map> implements Databases<T> {
    @Override
    public void connectToDB() {
        /* TODO Add realization connect to MariaDB */
    }

    @Override
    public void createTableFromFile(String ddlFile) {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void insertRecord(String tableName, T value) {

    }

    @Override
    public void updateRecord(String tableName, T set, T where) {

    }

    @Override
    public List<String> selectRecord(String tableName, T select, T where) {
        return null;
    }


}
