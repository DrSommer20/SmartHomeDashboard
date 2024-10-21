package mosbach.dhbw.de.smarthome.service.impl;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mosbach.dhbw.de.smarthome.config.PostgresConnectionPool;
import mosbach.dhbw.de.smarthome.model.Action;
import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.service.api.RoutineScheduler;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;

@Service
public class RoutineServicePostgres implements RoutineService {

    private static List<Routine> scheduledRoutines = new ArrayList<>();

    private static DataSource dataSource = PostgresConnectionPool.getDataSource();

    public static void initializeRoutines() {
        String selectRoutinesString = "SELECT * FROM group18_routine";
        String selectActionsString = "SELECT a.id, a.name, a.group18_device_id, d.name as device_name, d.group18_user_id FROM group18_action a JOIN group18_device d ON a.group18_device_id = d.id WHERE a.group18_routine_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(selectRoutinesString);
             ResultSet routineResultSet = routineStatement.executeQuery()) {
            while (routineResultSet.next()) {
                Routine routine = new Routine();
                routine.setId(routineResultSet.getInt("id"));
                routine.setName(routineResultSet.getString("name"));
                routine.setTriggerTime(routineResultSet.getString("trigger_time"));
                routine.setState(routineResultSet.getBoolean("state"));

                // Get actions for the routine
                try (PreparedStatement actionStatement = connection.prepareStatement(selectActionsString)) {
                    actionStatement.setInt(1, routine.getId());
                    ResultSet actionResultSet = actionStatement.executeQuery();
                    List<Action> actions = new ArrayList<>();
                    while (actionResultSet.next()) {
                        Action action = new Action();
                        action.setId(actionResultSet.getInt("id"));
                        action.setSetTo(actionResultSet.getString("name"));
                        action.setDeviceID(actionResultSet.getString("group18_device_id"));
                        action.setUser(actionResultSet.getInt("group18_user_id"));
                        
                        actions.add(action);
                    }
                    routine.setActions(actions);
                }
                scheduledRoutines.add(routine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initializeRoutine();
    }
    
    private static void initializeRoutine() {
        for (Routine routine : scheduledRoutines) {
            RoutineScheduler routineScheduler = new RoutineSchedulerImpl(routine.getActions());
            routine.setRoutineScheduler(routineScheduler);
            if (routine.isState()) {                
                routine.activateRoutine();
            }
        }
    }


    @Override
    public void addRoutine(int user, Routine routine) {
        String insertRoutineString = "INSERT INTO group18_routine (name, trigger_time, state, group18_user_id) VALUES (?, ?, ?, ?)";
        String insertActionString = "INSERT INTO group18_action (name, group18_device_id, group18_routine_id) VALUES (?, ?, ?)";
        
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            // Insert routine
            try (PreparedStatement routineStatement = connection.prepareStatement(insertRoutineString, PreparedStatement.RETURN_GENERATED_KEYS)) {
                routineStatement.setString(1, routine.getName());
                routineStatement.setString(2, routine.getTriggerTime());
                routineStatement.setBoolean(3, routine.isState());
                routineStatement.setInt(4, user);
                routineStatement.executeUpdate();

                // Get generated routine ID
                try (ResultSet generatedKeys = routineStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int routineId = generatedKeys.getInt(1);

                        // Insert actions
                        try (PreparedStatement actionStatement = connection.prepareStatement(insertActionString)) {
                            for (Action action : routine.getActions()) {
                                actionStatement.setString(1, action.getSetTo());
                                actionStatement.setString(2, action.getDeviceID());
                                actionStatement.setInt(3, routineId);
                                actionStatement.addBatch();
                            }
                            actionStatement.executeBatch();
                        }
                    }
                }

                scheduledRoutines.add(routine);RoutineScheduler routineScheduler = new RoutineSchedulerImpl(routine.getActions());
                routine.setRoutineScheduler(routineScheduler);
                if (routine.isState()) {
                    routine.activateRoutine();                    
                }
            }
            connection.commit(); ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Routine> getRoutines(int user) {
        String selectRoutinesString = "SELECT id, name, trigger_time, state FROM group18_routine WHERE group18_user_id = ?";
        String selectActionsString = "SELECT a.id, a.name, a.group18_device_id, d.name as device_name FROM group18_action a JOIN group18_device d ON a.group18_device_id = d.id WHERE a.group18_routine_id = ?";
        List<Routine> routines = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(selectRoutinesString)) {
            routineStatement.setInt(1, user);
            ResultSet routineResultSet = routineStatement.executeQuery();

            while (routineResultSet.next()) {
                Routine routine = new Routine();
                routine.setId(routineResultSet.getInt("id"));
                routine.setName(routineResultSet.getString("name"));
                routine.setTriggerTime(routineResultSet.getString("trigger_time"));
                routine.setState(routineResultSet.getBoolean("state"));

                // Get actions for the routine
                try (PreparedStatement actionStatement = connection.prepareStatement(selectActionsString)) {
                    actionStatement.setInt(1, routine.getId());
                    ResultSet actionResultSet = actionStatement.executeQuery();
                    List<Action> actions = new ArrayList<>();
                    while (actionResultSet.next()) {
                        Action action = new Action();
                        action.setId(actionResultSet.getInt("id"));
                        action.setSetTo(actionResultSet.getString("name"));
                        action.setDeviceID(actionResultSet.getString("group18_device_id"));
                        action.setDeviceName(actionResultSet.getString("device_name"));
                        
                        actions.add(action);
                    }
                    routine.setActions(actions);
                }

                routines.add(routine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routines;
    }


    @Override
    public Routine getRoutineByID(int id, int user) {
        String selectRoutineString = "SELECT id, name, trigger_time, state FROM group18_routine WHERE id = ? AND group18_user_id = ?";
        String selectActionsString = "SELECT a.id, a.name, a.group18_device_id, d.name as device_name FROM group18_action a JOIN group18_device d ON a.group18_device_id = d.id WHERE a.group18_routine_id = ?";
        Routine routine = null;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement routineStatement = connection.prepareStatement(selectRoutineString)) {
                routineStatement.setInt(1, id);
                routineStatement.setInt(2, user);
                ResultSet routineResultSet = routineStatement.executeQuery();
                if (routineResultSet.next()) {
                    routine = new Routine();
                    routine.setId(routineResultSet.getInt("id"));
                    routine.setName(routineResultSet.getString("name"));
                    routine.setTriggerTime(routineResultSet.getString("trigger_time"));
                    routine.setState(routineResultSet.getBoolean("state"));

                    // Retrieve actions
                    try (PreparedStatement actionStatement = connection.prepareStatement(selectActionsString)) {
                        actionStatement.setInt(1, routine.getId());
                        ResultSet actionResultSet = actionStatement.executeQuery();
                        List<Action> actions = new ArrayList<>();
                        while (actionResultSet.next()) {
                            Action action = new Action();
                            action.setId(actionResultSet.getInt("id"));
                            action.setSetTo(actionResultSet.getString("name"));
                            action.setDeviceID(actionResultSet.getString("group18_device_id"));
                            action.setDeviceName(actionResultSet.getString("device_name"));
                            actions.add(action);
                        }
                        routine.setActions(actions);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routine;
    }

    @Override
    public boolean deleteRoutine(int id, int user) {
        deleteActionByRoutineID(id);
        String deleteRoutineString = "DELETE FROM group18_routine WHERE id = ? AND group18_user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(deleteRoutineString)) {
            routineStatement.setInt(1, id);
            routineStatement.setInt(2, user);
            scheduledRoutines.stream().filter(routine -> routine.getId() == id).forEach(routine -> {
                routine.deactivateRoutine();
            });
            scheduledRoutines.removeIf(routine -> routine.getId() == id);
            return routineStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean deleteActionByRoutineID(int routineID) {
        String deleteActionString = "DELETE FROM group18_action WHERE group18_routine_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement actionStatement = connection.prepareStatement(deleteActionString)) {
            actionStatement.setInt(1, routineID);
            scheduledRoutines.stream().filter(routine -> routine.getId() == routineID).forEach(routine -> {
                routine.deactivateRoutine();
            });
            scheduledRoutines.removeIf(routine -> routine.getId() == routineID);
            return actionStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean switchRoutine(int id, boolean state, int user) {
        String updateRoutineString = "UPDATE group18_routine SET state = ? WHERE id = ? AND group18_user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(updateRoutineString)) {
            routineStatement.setBoolean(1, state);
            routineStatement.setInt(2, id);
            routineStatement.setInt(3, user);
            scheduledRoutines.stream().filter(routine -> routine.getId() == id).forEach(routine -> {
                if (state) {
                    routine.activateRoutine();
                } else {
                    routine.deactivateRoutine();
                }
            });
            return routineStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void updateRoutine(Routine routine, int userID) {
        String updateRoutineString = "UPDATE group18_routine SET name = ?, trigger_time = ? WHERE id = ? AND group18_user_id = ?";
        String updateActionString = "UPDATE group18_action SET name = ?, group18_device_id = ? WHERE id = ? AND group18_routine_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            // Update routine
            try (PreparedStatement routineStatement = connection.prepareStatement(updateRoutineString)) {
                routineStatement.setString(1, routine.getName());
                routineStatement.setString(2, routine.getTriggerTime());
                routineStatement.setInt(3, routine.getId());
                routineStatement.setInt(4, userID);
                routineStatement.executeUpdate();

                // Update actions
                try (PreparedStatement actionStatement = connection.prepareStatement(updateActionString)) {
                    for (Action action : routine.getActions()) {
                        actionStatement.setString(1, action.getSetTo());
                        actionStatement.setString(2, action.getDeviceID());
                        actionStatement.setInt(3, action.getId());
                        actionStatement.setInt(4, routine.getId());
                        actionStatement.addBatch();
                    }
                    actionStatement.executeBatch();
                }
                scheduledRoutines.removeIf(r -> r.getId() == routine.getId());
                scheduledRoutines.add(routine);
                RoutineScheduler routineScheduler = new RoutineSchedulerImpl(routine.getActions());
                routine.setRoutineScheduler(routineScheduler);
                if (routine.isState()) {
                    routine.activateRoutine();
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean deleteActionsByDevice(String deviceId) {
        String deleteActionsString = "DELETE FROM group18_action WHERE group18_device_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteActionsString)) {
            statement.setString(1, deviceId);
            int rowsAffected = statement.executeUpdate();
            scheduledRoutines.stream().filter(routine -> routine.getActions().get(0).getDeviceID().equals(deviceId)).forEach(routine -> {
                routine.deactivateRoutine();
            });
            scheduledRoutines.removeIf(routine -> routine.getActions().get(0).getDeviceID().equals(deviceId));
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
