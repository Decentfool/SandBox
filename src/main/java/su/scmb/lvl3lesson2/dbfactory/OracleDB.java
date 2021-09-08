package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.Config;
import su.scmb.lvl3lesson2.exception.InvalidFieldType;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class OracleDB<T extends Map> implements Databases<T> {
    private static final Logger logger = Logger.getLogger(OracleDB.class.getName());
    private Connection connection;

    protected OracleDB() {
    }

    @Override
    public void connectToDB() {
        try {
            connection = DriverManager.getConnection(Config.getURL(), Config.getUSERNAME(), Config.getPASSWORD());
            if (connection != null) {
                logger.info("Connection to DB Success");
            } else {
                logger.severe("Connection to DB Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTableFromFile(String ddlFile) {
        /* TODO Support many Create Table in one file */
        final StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(ddlFile))) {
            Stream<String> lines = bufferedReader.lines();
            lines.forEach((x) -> {
                stringBuilder.append(x + " ");
            });
            try(Statement statement = connection.createStatement()) {
                statement.execute(stringBuilder.toString());
            } catch (SQLException e) {
                logger.severe("Error in Create table " + e.getErrorCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void insertRecord(String tableName, T value) {
        final StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(0);
        value.forEach((x, y) -> {
            try {
                if (atomicInteger.get() == value.size() - 1) {
                    stringBuilder.append(Databases.super.valueAfterCast((String) x, (String) y));
                } else {
                    stringBuilder.append(Databases.super.valueAfterCast((String) x, (String) y) + ",");
                }
                atomicInteger.getAndIncrement();
            } catch (InvalidFieldType e) {
                e.printStackTrace();
            }
        });
        try(Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + tableName + " VALUES (" + stringBuilder + ")");

        } catch (SQLException e) {
            logger.severe("Error in Insert Record ");
            e.printStackTrace();

        }
    }
}
