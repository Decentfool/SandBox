package su.scmb.lvl3lesson2.dbfactory;

import su.scmb.lvl3lesson2.Config;
import su.scmb.lvl3lesson2.exception.InvalidFieldType;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class OracleDB<T extends Map> implements Databases<T> {
    private static final Logger LOGGER = Logger.getLogger(OracleDB.class.getName());
    private Connection connection;

    protected OracleDB() {
    }

    @Override
    public void connectToDB() {
        try {
            connection = DriverManager.getConnection(Config.getURL(), Config.getUSERNAME(), Config.getPASSWORD());
            connection.setAutoCommit(true);
            if (connection != null) {
                LOGGER.info("Connection to DB Success");
            } else {
                LOGGER.severe("Connection to DB Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTableFromFile(String ddlFile) {
        /* TODO Support many Create Table in one file */
        final StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ddlFile))) {
            Stream<String> lines = bufferedReader.lines();
            lines.forEach((x) -> {
                stringBuilder.append(x + " ");
            });
            try (Statement statement = connection.createStatement()) {
                statement.execute(stringBuilder.toString());
            } catch (SQLException e) {
                LOGGER.severe("Error in Create table " + e.getErrorCode());
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
        final StringBuffer stringFields = new StringBuffer();
        final StringBuffer stringValues = new StringBuffer();
        TreeMap<String, String> metadata = getMetadataTable(tableName);
        AtomicBoolean hasError = new AtomicBoolean(false);
        AtomicBoolean isFirst = new AtomicBoolean(true);
        String queryTemplate = "INSERT INTO ${tableName} (${tableFields}) VALUES (${tableValue})";
        if (!metadata.isEmpty()) {
            value.forEach((fieldName, valueField) -> {
                if (metadata.containsKey(fieldName)) {
                    try {
                        stringFields.append(isFirst.get() ? fieldName : ", " + fieldName + "");
                        stringValues.append(isFirst.get() ? valueAfterCast((String) valueField, metadata.get(fieldName))
                                : ", " + valueAfterCast((String) valueField, metadata.get(fieldName)) + "");
                        isFirst.set(false);
                    } catch (InvalidFieldType e) {
                        LOGGER.severe("Unknown field type " + fieldName);
                        hasError.set(true);
                    }
                } else {
                    LOGGER.severe("Unknown field name, name: " + fieldName);
                    hasError.set(true);
                    return;
                }
            });
            String queryResult = queryTemplate.replace("${tableName}", tableName)
                    .replace("${tableFields}", stringFields.toString())
                    .replace("${tableValue}", stringValues.toString());
            if (!hasError.get()) {
                try (Statement statement = connection.createStatement()) {
                    LOGGER.info("Result SQL String: " + queryResult);
                    statement.execute(queryResult);
                    LOGGER.info("Insert in " + tableName + " successfully complete");
                } catch (SQLException e) {
                    LOGGER.severe("Error in Insert Record ");
                }
            } else {
                LOGGER.severe("Query has error, please check inward parameters");
            }
        } else {
            LOGGER.warning("Metadata for " + tableName + " not found, insert will not be processing");
        }
    }

    @Override
    public void updateRecord(String tableName, T set, T where) {
        final StringBuffer stringSet = new StringBuffer();
        final StringBuffer stringWhere = new StringBuffer();
        AtomicBoolean hasError = new AtomicBoolean(false);
        AtomicBoolean isFirst = new AtomicBoolean(true);
        TreeMap<String, String> metadata = getMetadataTable(tableName);
        String queryTemplate = "UPDATE ${tableName} SET ${tableSet} WHERE ${tableWhere}";
        if (!metadata.isEmpty()) {
            set.forEach((fieldName, valueField) -> {
                if (metadata.containsKey(fieldName)) {
                    try {
                        stringSet.append(isFirst.get() ? fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName))
                                : ", " + fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName)));
                        isFirst.set(false);
                    } catch (InvalidFieldType e) {
                        LOGGER.severe("Unknown field type " + fieldName);
                        hasError.set(true);
                    }
                } else {
                    LOGGER.severe("Unknown field name, name: " + fieldName);
                    hasError.set(true);
                    return;
                }
            });
            isFirst.set(true);
            where.forEach((fieldName, valueField) -> {
                if (metadata.containsKey(fieldName)) {
                    try {
                        stringWhere.append(isFirst.get() ? fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName))
                                : " AND " + fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName)));
                        isFirst.set(false);
                    } catch (InvalidFieldType e) {
                        LOGGER.severe("Unknown field type " + fieldName);
                        hasError.set(true);
                    }
                } else {
                    LOGGER.severe("Unknown field name, name: " + fieldName);
                    hasError.set(true);
                    return;
                }
            });
            String queryResult = queryTemplate.replace("${tableName}", tableName)
                    .replace("${tableSet}", stringSet.toString())
                    .replace("${tableWhere}", stringWhere.toString());
            if (!hasError.get()) {
                try (Statement statement = connection.createStatement()) {
                    LOGGER.info("Result SQL String: " + queryResult);
                    statement.execute(queryResult);
                    if (statement.getUpdateCount() == 0) {
                        LOGGER.warning("Record for Update is not found");

                    } else {
                        LOGGER.info("Update in " + tableName + " successfully complete");
                    }

                } catch (SQLException e) {
                    LOGGER.severe("Error in Update Record");
                }
            } else {
                LOGGER.severe("Query has error, please check inward parameters");
            }
        } else {
            LOGGER.warning("Metadata for " + tableName + " not found, update will not be processing");
        }

    }

    @Override
    public List<String> selectRecord(String tableName, T select, T where) {
        final StringBuffer stringSelect = new StringBuffer();
        final StringBuffer stringWhere = new StringBuffer();
        final StringBuffer stringResult = new StringBuffer();
        AtomicBoolean hasError = new AtomicBoolean(false);
        AtomicBoolean isFirst = new AtomicBoolean(true);
        List<String> resultSelect = new ArrayList<>();
        TreeMap<String, String> metadata = getMetadataTable(tableName);
        String queryTemplate = "SELECT ${tableSelect} FROM ${tableName} WHERE ${tableWhere}";
        if (!metadata.isEmpty()) {
            select.forEach((fieldName, valueField) -> {
                if (metadata.containsKey(fieldName)) {
                    stringSelect.append(isFirst.get() ? fieldName : ", " + fieldName);
                    isFirst.set(false);
                } else {
                    LOGGER.severe("Unknown field name, name: " + fieldName);
                    hasError.set(true);
                    return;
                }
            });
            isFirst.set(true);
            where.forEach((fieldName, valueField) -> {
                if (metadata.containsKey(fieldName)) {
                    try {
                        stringWhere.append(isFirst.get() ? fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName))
                                : " AND " + fieldName + " = " + valueAfterCast((String) valueField, metadata.get(fieldName)));
                        isFirst.set(false);
                    } catch (InvalidFieldType e) {
                        LOGGER.severe("Unknown field type " + fieldName);
                        hasError.set(true);
                    }
                } else {
                    LOGGER.severe("Unknown field name, name: " + fieldName);
                    hasError.set(true);
                    return;
                }
            });
            String queryResult = queryTemplate.replace("${tableName}", tableName)
                    .replace("${tableSelect}", stringSelect.toString())
                    .replace("${tableWhere}", stringWhere.toString());
            if (!hasError.get()) {
                try (Statement statement = connection.createStatement()) {
                    LOGGER.info("Result SQL String: " + queryResult);
                    ResultSet resultSet = statement.executeQuery(queryResult);
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    while (resultSet.next()) {
                        for (int j = 1; j <= resultSetMetaData.getColumnCount(); j++) {
                            stringResult.append(resultSet.getString(j) + " ");
                        }
                        resultSelect.add(stringResult.toString());
                        stringResult.setLength(0);
                    }
                    return resultSelect;
                } catch (SQLException e) {
                    LOGGER.severe("Error in Select Record");
                }
            } else {
                LOGGER.severe("Query has error, please check inward parameters");
            }
        } else {
            LOGGER.warning("Metadata for " + tableName + " not found, select will not be processing");
        }
        return null;
    }

    private TreeMap getMetadataTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            TreeMap<String, String> fieldMetadata = new TreeMap<>();
            String query = "SELECT COLUMN_NAME, DATA_TYPE FROM USER_TAB_COLS WHERE TABLE_NAME = '" + tableName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                fieldMetadata.put(resultSet.getString("COLUMN_NAME"), resultSet.getString("DATA_TYPE"));
            }
            return fieldMetadata;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String valueAfterCast(String value, String type) throws InvalidFieldType {
        String result;
        switch (type) {
            case "VARCHAR2":
                result = "'" + value + "'";
                break;
            case "NUMBER":
                result = "" + value;
                break;
            default:
                throw new InvalidFieldType();
        }
        return result;
    }
}
