package com.epam.audiomanager.database.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDb {
    //constant fields
    private static final Logger LOGGER = LogManager.getLogger(ConnectorDb.class);
    private static final String PATH_TO_DATABASE_PROPERTIES = "resources/database";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";

    public static ProxyConnection getConnection() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PATH_TO_DATABASE_PROPERTIES);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = resourceBundle.getString(URL);
            String user = resourceBundle.getString(USER);
            String pass = resourceBundle.getString(PASSWORD);
            return new ProxyConnection(DriverManager.getConnection(url, user, pass));
        } catch (ClassNotFoundException | SQLException e) {
            //TODO catching
            LOGGER.error("Problem with database", e);
            throw new RuntimeException("Problem with database", e);
        }
    }
}

