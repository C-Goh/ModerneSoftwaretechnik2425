package softwaretechnik.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteManager {
    private static final String JDBC_URL = "jdbc:sqlite:softwaretechnik.db";
    private static final Logger LOGGER = Logger.getLogger(SQLiteManager.class.getName());


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    public static void initializeDatabase() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            final String sql = "CREATE TABLE IF NOT EXISTS example_table (id INTEGER PRIMARY KEY, name TEXT)";
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization error", e);
        }
    }
}
