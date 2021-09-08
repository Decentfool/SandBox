package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.exception.InvalidFieldType;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

public interface Databases<T extends Map> {
    void connectToDB();
    void createTableFromFile(String ddlFile);
    Connection getConnection();
    void insertRecord(String tableName, T value);
    default String valueAfterCast(String value, String type) throws InvalidFieldType {
        String result;
        switch (type) {
            case "VARCHAR":
                result = "'" + value + "'";
                break;
            case "INTEGER":
                result = "" + value;
                break;
            default:
                throw new InvalidFieldType();
        }
        return result;
    }
}
