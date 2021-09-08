package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.exception.InvalidFieldType;

import java.sql.Connection;
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
    public String valueAfterCast(String value, String type) throws InvalidFieldType {
        return null;
    }

}
