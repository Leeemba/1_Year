package server.db;

import common.models.Address;
import common.models.Coordinates;
import common.models.Organization;
import common.models.OrganizationType;

import java.sql.*;
import java.util.*;

public class OrganizationRepo {

    public  Queue<Organization> getAllOrganizations(){
        String sql = "SELECT * FROM organizations";
        Queue<Organization> result = new ArrayDeque<>();
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Organization organization = new Organization();
                organization.setId(resultSet.getInt("id"));
                organization.setName(resultSet.getString("name"));
                organization.setCoordinates(new Coordinates(resultSet.getLong("coord_x"), resultSet.getObject("coord_y",Double.class)));
                organization.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate());
                organization.setAnnualTurnover(resultSet.getObject("annual_turnover", Integer.class));
                organization.setFullName(resultSet.getString("full_name"));
                String type = resultSet.getString("type");
                organization.setType(type == null ? null : OrganizationType.valueOf(type));
                organization.setPostalAddress(new Address(resultSet.getString("address_zipcode")));
                organization.setCreator(resultSet.getString("creator_login"));

                result.add(organization);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading from database", e);
        }

        return result;
    }
    public static void bindOrg(PreparedStatement statement, Organization organization) throws SQLException {
        statement.setString(1, organization.getName());
        statement.setLong(2, organization.getCoordinates().getX());
        statement.setDouble(3, organization.getCoordinates().getY());
        statement.setTimestamp(4, Timestamp.valueOf(organization.getCreationDate().atStartOfDay()));

        if (organization.getAnnualTurnover() != null) {
            statement.setInt(5, organization.getAnnualTurnover());
        } else {
            statement.setNull(5, Types.INTEGER);
        }

        statement.setString(6, organization.getFullName());
        statement.setString(7, organization.getType().name());
        if (organization.getPostalAddress().getZipCode()!=null){
            statement.setString(8,organization.getPostalAddress().getZipCode());
        }else {
            statement.setNull(8,Types.VARCHAR);
        }
        statement.setString(9, organization.getCreator());
    }

    public static Integer insert(Organization organization) {
        String sql = """
                INSERT INTO organizations
                  (name, coord_x, coord_y, creation_date,
                   annual_turnover, full_name, type,address_zipcode,
                   creator_login)
                VALUES (?,?,?,?,?,?,?,?,?)
                RETURNING id
                """;
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            bindOrg(ps, organization);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Error inserting org"+e.getMessage()+e.getCause());
        }
        return null;
    }
    public static int deleteAllByUser(String login) {
        String sql = "DELETE FROM organizations WHERE creator_login = ?";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            return ps.executeUpdate();

        } catch (SQLException e) {
            return -1;
        }
    }

    public boolean deleteById(int id, String login) {
        String sql = "DELETE FROM tickets WHERE id = ? AND creator_login = ?";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.setString(2, login);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(int id, Organization organization, String login) {
        String sql = """
                UPDATE organizations SET
                  name = ?, coord_x = ?, coord_y = ?, creation_date = ?,
                  annual_turnover = ?, full_name = ?, type = ?, address_zipcode = ?,
                WHERE id = ? AND creator_login = ?
                """;
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            bindOrg(ps, organization);

            ps.setInt(9, id);
            ps.setString(10, login);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }



}


