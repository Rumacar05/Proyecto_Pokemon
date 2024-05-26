package database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabase {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();

        properties.load(new FileReader("src/main/resources/config/sql.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
    }
}
