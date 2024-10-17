package mosbach.dhbw.de.smarthome.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.config.PostgresConnectionPool;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.AuthService;
import mosbach.dhbw.de.smarthome.service.api.RoomService;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@Service
public class UserServicePostgres implements UserService {

    private DataSource dataSource = PostgresConnectionPool.getDataSource();

    @Autowired
    private AuthService authService;

    @Autowired
    private RoomService roomService;

    public static void main(String[] args) {
        UserServicePostgres usps = new UserServicePostgres();
        System.out.println(usps.getUserById(2).getFirstName()); ;
    }

    @Override
    public void addUser(User user) {
        String insertUserString = "INSERT INTO \"group18_user\" (firstName, lastName, email, password, pat, isVerified) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserString)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPat());
            preparedStatement.setBoolean(6, user.isVerified());
            preparedStatement.executeUpdate();

            roomService.addRoom(new Room("Default Room"), user.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM \"group18_user\" WHERE email = ?";
        User user = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordWithoutEncode(resultSet.getString("password"));
                user.setPat(resultSet.getString("pat"));
                user.setVerified(resultSet.getBoolean("isVerified"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean deleteUser(String email) {
        String query = "DELETE FROM \"group18_user\" WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUser(String token) {
        return getUserByEmail(authService.extractEmail(token));
    }

    @Override
    public String getUserPATbyID(int userID) {
        return getUserById(userID).getPat();
    }

    @Override
    public User getUserById(int userID) {
        String query = "SELECT * FROM \"group18_user\" WHERE id = ?";
        User user = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordWithoutEncode(resultSet.getString("password"));
                user.setPat(resultSet.getString("pat"));
                user.setVerified(resultSet.getBoolean("isVerified"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE \"group18_user\" SET firstName = ?, lastName = ?, email = ?, password = ?, pat = ?, isVerified = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPat());
            preparedStatement.setBoolean(6, user.isVerified());
            preparedStatement.setInt(7, user.getUserID());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean verifyUser(String email) {
        String query = "UPDATE \"group18_user\" SET isVerified = true WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}