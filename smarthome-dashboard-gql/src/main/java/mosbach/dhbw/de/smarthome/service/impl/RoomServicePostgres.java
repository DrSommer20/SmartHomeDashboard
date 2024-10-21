package mosbach.dhbw.de.smarthome.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.config.PostgresConnectionPool;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.service.api.RoomService;

@Service
public class RoomServicePostgres implements RoomService{

    DataSource dataSource = PostgresConnectionPool.getDataSource();

    @Override
    public List<Room> getRooms(int userID) {
        String selectRoomsString = "SELECT id, name " +
                                "FROM group18_room " +
                                "WHERE group18_user_id = ?";
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectRoomsString)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Room room = new Room();
               room.setId(resultSet.getInt("id"));
               room.setName(resultSet.getString("name"));
               rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room getRoomById(int roomId, int userID) {
        String selectRoomsString = "SELECT id, name " +
                                "FROM group18_room " +
                                "WHERE group18_user_id = ? AND id = ?";
        Room room = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectRoomsString)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void addRoom(Room room, int userID) {
        String insertUserString = "INSERT INTO group18_room "+
                                "(name, group18_user_id) " +
                                "VALUES (? ,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserString)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(Room room, int userID) {
        String updateRoomString = "UPDATE group18_room SET name = ? WHERE id = ? AND group18_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateRoomString)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setInt(2, room.getId());
            preparedStatement.setInt(3, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeRoom(int roomId, int userID) {
        String getDefaultRoomIdQuery = "SELECT id FROM group18_room WHERE name = 'Default Room' AND group18_user_id = ?";
        String updateDevicesQuery = "UPDATE group18_device SET group18_room_id = ? WHERE group18_room_id = ?";
        String deleteRoomString = "DELETE FROM group18_room WHERE id = ? AND group18_user_id = ?";
        
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Start transaction
    
            // Get the ID of the Default Room
            int defaultRoomId;
            try (PreparedStatement getDefaultRoomIdStmt = connection.prepareStatement(getDefaultRoomIdQuery)) {
                getDefaultRoomIdStmt.setInt(1, userID);
                try (ResultSet resultSet = getDefaultRoomIdStmt.executeQuery()) {
                    if (resultSet.next()) {
                        defaultRoomId = resultSet.getInt("id");
                    } else {
                        connection.rollback();
                        return false; // Default Room not found
                    }
                }
            }
    
            // Update devices to be assigned to the Default Room
            try (PreparedStatement updateDevicesStmt = connection.prepareStatement(updateDevicesQuery)) {
                updateDevicesStmt.setInt(1, defaultRoomId);
                updateDevicesStmt.setInt(2, roomId);
                updateDevicesStmt.executeUpdate();
            }
    
            // Delete the room
            try (PreparedStatement deleteRoomStmt = connection.prepareStatement(deleteRoomString)) {
                deleteRoomStmt.setInt(1, roomId);
                deleteRoomStmt.setInt(2, userID);
                int rowsAffected = deleteRoomStmt.executeUpdate();
                connection.commit(); // Commit transaction
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
