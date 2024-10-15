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
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;

@Service
public class RoutineServicePostgre implements RoutineService {

    private DataSource dataSource = PostgresConnectionPool.getDataSource();


    @Override
    public void addRoutine(User user, Routine routine) {
        String insertRoutineString = "INSERT INTO smartHome_routine (name, trigger_time, state, smartHome_user_id) VALUES (?, ?, ?, ?)";
        String insertActionString = "INSERT INTO smartHome_action (name, smartHome_device_id, smartHome_routine_id) VALUES (?, ?, ?)";
        
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            // Insert routine
            try (PreparedStatement routineStatement = connection.prepareStatement(insertRoutineString, PreparedStatement.RETURN_GENERATED_KEYS)) {
                routineStatement.setString(1, routine.getName());
                routineStatement.setString(2, routine.getTriggerTime());
                routineStatement.setBoolean(3, routine.isState());
                routineStatement.setInt(4, user.getUserID());
                routineStatement.executeUpdate();

                // Get generated routine ID
                try (ResultSet generatedKeys = routineStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int routineId = generatedKeys.getInt(1);

                        // Insert actions
                        try (PreparedStatement actionStatement = connection.prepareStatement(insertActionString)) {
                            for (Action action : routine.getActions()) {
                                actionStatement.setString(1, action.getAction());
                                actionStatement.setInt(2, action.getDeviceID());
                                actionStatement.setInt(3, routineId);
                                actionStatement.addBatch();
                            }
                            actionStatement.executeBatch();
                        }
                    }
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Routine> getRoutines(User user) {
        String selectRoutinesString = "SELECT id, name, trigger_time, state FROM smartHome_routine WHERE smartHome_user_id = ?";
        String selectActionsString = "SELECT id, name, smartHome_device_id FROM smartHome_action WHERE smartHome_routine_id = ?";
        List<Routine> routines = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(selectRoutinesString)) {
            routineStatement.setInt(1, user.getUserID());
            ResultSet routineResultSet = routineStatement.executeQuery();

            while (routineResultSet.next()) {
                Routine routine = new Routine();
                routine.setID(routineResultSet.getInt("id"));
                routine.setName(routineResultSet.getString("name"));
                routine.setTriggerTime(routineResultSet.getString("trigger_time"));
                routine.setState(routineResultSet.getBoolean("state"));

                // Get actions for the routine
                try (PreparedStatement actionStatement = connection.prepareStatement(selectActionsString)) {
                    actionStatement.setInt(1, routine.getID());
                    ResultSet actionResultSet = actionStatement.executeQuery();
                    List<Action> actions = new ArrayList<>();
                    while (actionResultSet.next()) {
                        Action action = new Action();
                        action.setID(actionResultSet.getInt("id"));
                        action.setAction(actionResultSet.getString("name"));
                        action.setDeviceID(actionResultSet.getInt("smartHome_device_id"));
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
    public Routine getRoutineByID(String id, User user) {
        String selectRoutineString = "SELECT id, name, trigger_time, state FROM smartHome_routine WHERE id = ? AND smartHome_user_id = ?";
        String selectActionsString = "SELECT id, name, smartHome_device_id FROM smartHome_action WHERE smartHome_routine_id = ?";
        Routine routine = null;
        try (Connection connection = dataSource.getConnection()) {
            // Retrieve routine
            try (PreparedStatement routineStatement = connection.prepareStatement(selectRoutineString)) {
                routineStatement.setInt(1, Integer.parseInt(id));
                routineStatement.setInt(2, user.getUserID());
                ResultSet routineResultSet = routineStatement.executeQuery();
                if (routineResultSet.next()) {
                    routine = new Routine();
                    routine.setID(routineResultSet.getInt("id"));
                    routine.setName(routineResultSet.getString("name"));
                    routine.setTriggerTime(routineResultSet.getString("trigger_time"));
                    routine.setState(routineResultSet.getBoolean("state"));

                    // Retrieve actions
                    try (PreparedStatement actionStatement = connection.prepareStatement(selectActionsString)) {
                        actionStatement.setInt(1, routine.getID());
                        ResultSet actionResultSet = actionStatement.executeQuery();
                        List<Action> actions = new ArrayList<>();
                        while (actionResultSet.next()) {
                            Action action = new Action();
                            action.setID(actionResultSet.getInt("id"));
                            action.setAction(actionResultSet.getString("name"));
                            action.setDeviceID(actionResultSet.getInt("smartHome_device_id"));
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
    public boolean deleteRoutine(String id, User user) {
        deleteActionByRoutineID(Integer.parseInt(id));
        String deleteRoutineString = "DELETE FROM smartHome_routine WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(deleteRoutineString)) {
            routineStatement.setInt(1, Integer.parseInt(id));
            routineStatement.setInt(2, user.getUserID());
            return routineStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteActionByRoutineID(int routineID) {
        String deleteActionString = "DELETE FROM smartHome_action WHERE smartHome_routine_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement actionStatement = connection.prepareStatement(deleteActionString)) {
            actionStatement.setInt(1, routineID);
            return actionStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean switchRoutine(String id, boolean state, User user) {
        String updateRoutineString = "UPDATE smartHome_routine SET state = ? WHERE id = ? AND smartHome_user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement routineStatement = connection.prepareStatement(updateRoutineString)) {
            routineStatement.setBoolean(1, state);
            routineStatement.setInt(2, Integer.parseInt(id));
            routineStatement.setInt(3, user.getUserID());
            return routineStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateRoutine(Routine routine, int userID) {
        String updateRoutineString = "UPDATE smartHome_routine SET name = ?, trigger_time = ? WHERE id = ? AND smartHome_user_id = ?";
        String updateActionString = "UPDATE smartHome_action SET name = ?, smartHome_device_id = ? WHERE id = ? AND smartHome_routine_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            // Update routine
            try (PreparedStatement routineStatement = connection.prepareStatement(updateRoutineString)) {
                routineStatement.setString(1, routine.getName());
                routineStatement.setString(2, routine.getTriggerTime());
                routineStatement.setInt(3, routine.getID());
                routineStatement.setInt(4, userID);
                routineStatement.executeUpdate();

                // Update actions
                try (PreparedStatement actionStatement = connection.prepareStatement(updateActionString)) {
                    for (Action action : routine.getActions()) {
                        actionStatement.setString(1, action.getAction());
                        actionStatement.setInt(2, action.getDeviceID());
                        actionStatement.setInt(3, action.getID());
                        actionStatement.setInt(4, routine.getID());
                        actionStatement.addBatch();
                    }
                    actionStatement.executeBatch();
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
