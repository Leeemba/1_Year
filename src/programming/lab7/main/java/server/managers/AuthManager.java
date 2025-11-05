package server.managers;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import server.db.DatabaseHandler;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthManager {
    private final int SALT_LENGTH = 16;
    private final String pepper;


    public AuthManager(String pepper) {

        this.pepper = pepper;
    }

    public boolean register(String login, String plainPassword) throws SQLException {
        String salt = generateSalt();
        String hashedPassword = generatePasswordHash(plainPassword,salt);
        String sql = "INSERT INTO users (login, password_hash, salt) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, hashedPassword);
            statement.setString(3, salt);

            return statement.executeUpdate() == 1;
        }
    }
    public boolean authenticate(String login, String plainPassword) throws SQLException {
        String sql = "SELECT password_hash, salt FROM users WHERE login = ?";

        try (Connection connection = DatabaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) return false;

            String expectedHash = resultSet.getString("password_hash");
            String salt = resultSet.getString("salt");
            String calculatedHash = generatePasswordHash(plainPassword,salt);

            return calculatedHash.equals(expectedHash);
        }
    }


    private String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
    }

    private String generatePasswordHash(String password, String salt) {
        return Hashing.sha256()
                .hashString(pepper + password + salt, StandardCharsets.UTF_8)
                .toString();
    }
}