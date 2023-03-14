package ru.hse.sql;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@UtilityClass
public class ConnectionFactory {
    public static Connection createConnection() {
        Properties properties = new Properties();
        Connection connection = null;

        try (InputStream propertiesStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties"))
        {
            properties.load(propertiesStream);

            String url = properties.getProperty("postgres.url");
            String username = properties.getProperty("postgres.username");
            String password = properties.getProperty("postgres.password");

            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
