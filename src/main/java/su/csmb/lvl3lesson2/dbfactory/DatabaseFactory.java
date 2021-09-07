package su.csmb.lvl3lesson2.dbfactory;

import su.csmb.lvl3lesson2.exception.ReservedForFuture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {

    private DatabaseFactory() {
    }

    public Connection getConnection(DatabaseTypes databaseTypes) {
        Connection connection = null;
        switch (databaseTypes) {
            case ORACLE:
                try {
                    connection = DriverManager.getConnection("111", "22", "2");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case MARIADB:
                try {
                    throw new ReservedForFuture();
                } catch (ReservedForFuture reservedForFuture) {
                    reservedForFuture.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid database type: " + databaseTypes);

        }
        return connection;
    }
    public static DatabaseFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final DatabaseFactory INSTANCE = new DatabaseFactory();
    }
}
