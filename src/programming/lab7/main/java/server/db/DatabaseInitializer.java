package server.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String[] DROP_DB_SQL = {
            """ 
            DROP TABLE IF EXISTS organizations, users CASCADE
            """,

            """
            DROP SEQUENCE IF EXISTS organization_id_seq CASCADE
            """
    };

    private static final String[] INIT_DB_SQL = {
            "CREATE SEQUENCE IF NOT EXISTS organization_id_seq START 1",

            """
            CREATE TABLE IF NOT EXISTS users (
                    login VARCHAR(40) PRIMARY KEY,
                    password_hash VARCHAR(400) NOT NULL,
                    salt CHAR(16) NOT NULL
                    )
            """,

            """
            CREATE TABLE IF NOT EXISTS organizations (
                    id INTEGER PRIMARY KEY DEFAULT nextval('organization_id_seq'),
                    name VARCHAR NOT NULL,
                    coord_x BIGINT NOT NULL,
                    coord_y DOUBLE PRECISION NOT NULL,
                    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                    annual_turnover INTEGER,
                    full_name VARCHAR(818) NOT NULL,
                    type VARCHAR NOT NULL,
                    address_zipcode VARCHAR(26),
                    creator_login VARCHAR REFERENCES users(login) NOT NULL
                    )
            """
    };


    public static void init() {
        try (Connection connection = DatabaseHandler.getConnection();
             Statement statement = connection.createStatement()) {

            for (String ddl : INIT_DB_SQL) {
                statement.execute(ddl);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    public static void reinitialize() {
        try (Connection connection = DatabaseHandler.getConnection();
             Statement statement = connection.createStatement()) {

            for (String ddl : DROP_DB_SQL) {
                statement.execute(ddl);
            }

            init();
        } catch (SQLException e) {
            throw new RuntimeException("Database dropping failed", e);
        }
    }
}
