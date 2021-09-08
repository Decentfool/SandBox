package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.exception.ReservedForFuture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {

    private DatabaseFactory() {
    }

    public Databases getConnection(DatabaseTypes databaseTypes) {
        Databases databases = null;
        switch (databaseTypes) {
            case ORACLE:
                databases = new OracleDB();
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
        return databases;
    }
    public static DatabaseFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final DatabaseFactory INSTANCE = new DatabaseFactory();
    }
}
