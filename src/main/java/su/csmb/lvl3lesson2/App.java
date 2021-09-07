package su.csmb.lvl3lesson2;

import su.csmb.lvl3lesson2.dbfactory.DatabaseFactory;
import su.csmb.lvl3lesson2.dbfactory.DatabaseTypes;
import su.csmb.lvl3lesson2.dbfactory.Databases;

import java.sql.Connection;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Config.loadConfiguration();
        DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
        Connection connection = databaseFactory.getConnection(DatabaseTypes.ORACLE);

       // Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

    }
}
