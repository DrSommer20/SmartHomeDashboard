package mosbach.dhbw.de.smarthome.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.config.PostgresConnectionPool;
import mosbach.dhbw.de.smarthome.dto.AllTypes;
import mosbach.dhbw.de.smarthome.dto.DeviceType;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;

@Service
public class DeviceServicePostgres implements DeviceService{

    DataSource dataSource = PostgresConnectionPool.getDataSource();

    @Autowired
    private RoutineService routineService;

    @Override
    public void addDevice(Device device, Integer userID) {
        String insertUserString = "INSERT INTO \"group18_device\" (id, name, status, group18_deviceType_id, group18_room_id, group18_user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserString)) {
            preparedStatement.setString(1, device.getId());
            preparedStatement.setString(2, device.getName());
            preparedStatement.setBoolean(3, device.getStatusBoolean());
            preparedStatement.setInt(4, device.getTypeID());
            preparedStatement.setInt(5, device.getLocation());
            preparedStatement.setInt(6, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Device> getDevices(Integer userID) {
        String selectDevicesString =    "SELECT d.id, d.name, d.status, d.group18_deviceType_id, d.group18_room_id, dt.name AS deviceTypeName, dt.icon AS deviceTypeIcon, " +
                                        "r.name AS roomName, r.id AS roomId " +
                                        "FROM group18_device d " +
                                        "JOIN group18_deviceType dt ON d.group18_deviceType_id = dt.id " +
                                        "JOIN group18_room r ON d.group18_room_id = r.id " +
                                        "WHERE d.group18_user_id = ?";
        List<Device> devices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectDevicesString)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Device device = new Device();
                device.setId(resultSet.getString("id"));
                device.setName(resultSet.getString("name"));
                device.setStatus(resultSet.getBoolean("status"));
                device.setTypeID(resultSet.getInt("group18_deviceType_id"));
                device.setLocation(resultSet.getInt("group18_room_id"));
                device.setType(resultSet.getString("deviceTypeName"));
                device.setTypeIcon(resultSet.getString("deviceTypeIcon"));
                device.setLocation(resultSet.getInt("roomId"));
                device.setRoomName(resultSet.getString("roomName"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    @Override
    public Device getDeviceById(String id, Integer userID) {
        String selectDeviceString = "SELECT d.id, d.name, d.status, d.group18_deviceType_id, d.group18_room_id, dt.name AS deviceTypeName, dt.icon AS deviceTypeIcon " +
                                "FROM group18_device d " +
                                "JOIN group18_deviceType dt ON d.group18_deviceType_id = dt.id " +
                                "WHERE d.id = ? AND d.group18_user_id = ?";
        Device device = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectDeviceString)) {
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                device = new Device();
                device.setId(resultSet.getString("id"));
                device.setName(resultSet.getString("name"));
                device.setStatus(resultSet.getBoolean("status"));
                device.setTypeID(resultSet.getInt("group18_deviceType_id"));
                device.setLocation(resultSet.getInt("group18_room_id"));
                device.setType(resultSet.getString("deviceTypeName"));
                device.setTypeIcon(resultSet.getString("deviceTypeIcon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }

    @Override
    public boolean deleteDevice(String id, Integer userID) {
        routineService.deleteActionsByDevice(id);
        String deleteDeviceString = "DELETE FROM \"group18_device\" WHERE id = ? AND group18_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteDeviceString)) {
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDevice(Device device, Integer userID) {
        String updateDeviceString = "UPDATE \"group18_device\" SET name = ?, status = ?, group18_deviceType_id = ?, group18_room_id = ? WHERE id = ? AND group18_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateDeviceString)) {
            preparedStatement.setString(1, device.getName());
            preparedStatement.setBoolean(2, device.getStatusBoolean());
            preparedStatement.setInt(3, device.getTypeID());
            preparedStatement.setInt(4, device.getLocation());
            preparedStatement.setString(5, device.getId());
            preparedStatement.setInt(6, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AllTypes getTypes() {
        String selectTypesString = "SELECT * FROM group18_deviceType";
        AllTypes allTypes = null;
        List<DeviceType> types = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectTypesString)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DeviceType type = new DeviceType();
                type.setTypeID(resultSet.getInt("id"));
                type.setType(resultSet.getString("name"));
                type.setTypeIcon(resultSet.getString("icon"));
                types.add(type);
            }
            allTypes = new AllTypes(types);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTypes;
    }


}
