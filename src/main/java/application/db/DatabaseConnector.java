package application.db;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DatabaseConnector {
    private Connection connect = null;

    public Connection getConnection() throws IOException, SQLException {
        String url = "";
        String user = "";
        String pass = "";

        try (FileInputStream inputStream =
                     new FileInputStream("src/main/java/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("db.URL");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.pass");

            connect = DriverManager.getConnection(url, user, pass);
        } catch (FileNotFoundException ex) {
            log.error("Couldn't find input file: " + ex);
        }
        return connect;
    }

    public void closeConnection() {
        try {
            if (connect != null) {
                connect.close();
            } else {
                log.info("Connection already closed");
            }
        } catch (SQLException ex) {
            log.error("Couldn't close connection: " + ex);
        }
    }
}
