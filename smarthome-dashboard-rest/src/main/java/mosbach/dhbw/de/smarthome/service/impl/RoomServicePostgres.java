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
    public List<Room> getRooms(Integer userID) {
        String selectRoomsString = "SELECT id, name" +
                                "FROM smartHome_device d " +
                                "WHERE smartHome_user_id = ?";
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectRoomsString)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Room room = new Room();
               room.setRoomId(resultSet.getInt("id"));
               room.setName(resultSet.getString("name"));
               rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room getRoomById(String roomId, Integer userID) {
        String selectRoomsString = "SELECT id, name" +
                                "FROM smartHome_device d " +
                                "WHERE smartHome_user_id = ? AND id = ?";
        Room room = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectRoomsString)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, Integer.parseInt(roomId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
                room.setRoomId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void addRoom(Room room, Integer userID) {
        String insertUserString = "INSERT INTO smartHome_room"+
                                "(id, name, smartHome_user_id)" +
                                "VALUES (? ,? ,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserString)) {
            preparedStatement.setInt(1, room.getRoomId());
            preparedStatement.setString(2, room.getName());
            preparedStatement.setInt(3, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(Room room, Integer userID) {
        String updateRoomString = "UPDATE smartHome_room SET name = ? WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateRoomString)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setInt(2, room.getRoomId());
            preparedStatement.setInt(3, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeRoom(String roomId, Integer userID) {
        String deleteRoomString = "DELETE FROM smartHome_room WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRoomString)) {
            preparedStatement.setInt(1, Integer.parseInt(roomId));
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
