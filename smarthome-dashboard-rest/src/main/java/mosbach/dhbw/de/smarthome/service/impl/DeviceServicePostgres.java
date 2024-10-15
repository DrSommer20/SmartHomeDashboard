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
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;

@Service
public class DeviceServicePostgres implements DeviceService{

    DataSource dataSource = PostgresConnectionPool.getDataSource();

    @Override
    public void addDevice(Device device, Integer userID) {
        String insertUserString = "INSERT INTO \"smartHome_device\" (name, status, smartHome_deviceType_id, smartHome_room_id, smartHome_user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserString)) {
            preparedStatement.setString(1, device.getName());
            preparedStatement.setBoolean(2, device.getStatusBoolean());
            preparedStatement.setInt(3, device.getTypeID());
            preparedStatement.setInt(4, device.getLocation());
            preparedStatement.setInt(5, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Device> getDevices(Integer userID) {
        String selectDevicesString = "SELECT d.id, d.name, d.status, d.smartHome_deviceType_id, d.smartHome_room_id, dt.name AS deviceTypeName, dt.icon AS deviceTypeIcon " +
                                "FROM smartHome_device d " +
                                "JOIN smartHome_deviceType dt ON d.smartHome_deviceType_id = dt.id " +
                                "WHERE d.smartHome_user_id = ?";
        List<Device> devices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectDevicesString)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Device device = new Device();
               device.setId(resultSet.getInt("id"));
               device.setName(resultSet.getString("name"));
               device.setStatus(resultSet.getBoolean("status"));
               device.setTypeID(resultSet.getInt("smartHome_deviceType_id"));
               device.setLocation(resultSet.getInt("smartHome_room_id"));
               device.setType(resultSet.getString("deviceTypeName"));
                device.setTypeIcon(resultSet.getString("deviceTypeIcon"));
               devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    @Override
    public Device getDeviceById(int id, Integer userID) {
        String selectDeviceString = "SELECT d.id, d.name, d.status, d.smartHome_deviceType_id, d.smartHome_room_id, dt.name AS deviceTypeName, dt.icon AS deviceTypeIcon " +
                                "FROM smartHome_device d " +
                                "JOIN smartHome_deviceType dt ON d.smartHome_deviceType_id = dt.id " +
                                "WHERE d.id = ? AND d.smartHome_user_id = ?";
        Device device = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectDeviceString)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                device = new Device();
                device.setId(resultSet.getInt("id"));
                device.setName(resultSet.getString("name"));
                device.setStatus(resultSet.getBoolean("status"));
                device.setTypeID(resultSet.getInt("smartHome_deviceType_id"));
                device.setLocation(resultSet.getInt("smartHome_room_id"));
                device.setType(resultSet.getString("deviceTypeName"));
                device.setTypeIcon(resultSet.getString("deviceTypeIcon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }

    @Override
    public boolean deleteDevice(int id, Integer userID) {
        String deleteDeviceString = "DELETE FROM \"smartHome_device\" WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteDeviceString)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDevice(Device device, Integer userID) {
        String updateDeviceString = "UPDATE \"smartHome_device\" SET name = ?, status = ?, smartHome_deviceType_id = ?, smartHome_room_id = ? WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateDeviceString)) {
            preparedStatement.setString(1, device.getName());
            preparedStatement.setBoolean(2, device.getStatusBoolean());
            preparedStatement.setInt(3, device.getTypeID());
            preparedStatement.setInt(4, device.getLocation());
            preparedStatement.setInt(5, device.getId());
            preparedStatement.setInt(6, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
