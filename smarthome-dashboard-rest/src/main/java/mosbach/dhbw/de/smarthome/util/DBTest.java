package mosbach.dhbw.de.smarthome.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import mosbach.dhbw.de.smarthome.config.PostgresConnectionPool;
import mosbach.dhbw.de.smarthome.model.Action;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;
import mosbach.dhbw.de.smarthome.service.api.RoomService;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;
import mosbach.dhbw.de.smarthome.service.api.UserService;
import mosbach.dhbw.de.smarthome.service.impl.DeviceServicePostgres;
import mosbach.dhbw.de.smarthome.service.impl.RoomServicePostgres;
import mosbach.dhbw.de.smarthome.service.impl.RoutineServicePostgre;
import mosbach.dhbw.de.smarthome.service.impl.UserServicePostgres;

public class DBTest {

    static UserService userService = new UserServicePostgres();
    static RoomService roomService = new RoomServicePostgres();
    static DeviceService deviceService = new DeviceServicePostgres();
    static RoutineService routineService = new RoutineServicePostgre();
    private static DataSource dataSource = PostgresConnectionPool.getDataSource();

    public static void main(String[] args) {
        User user = userService.getUserById(1);
        
        routineService.getRoutines(user);

        getAllRoutines();

    }

    public static void alterRoutineTable() {
        String sql = "ALTER TABLE group18_action ALTER COLUMN name TYPE VARCHAR(50)";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void getAllRoutines() {
        String sql = "SELECT * FROM group18_routine";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int userId = resultSet.getInt("group18_user_id");
                System.out.println("Routine ID: " + id + ", Name: " + name + ", User ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
}
