package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;

public interface RoutineService {

    /**
     * Adds a routine to the database
     * 
     * @param user the user
     * @param routine the routine
     */
    public void addRoutine(User user, Routine routine);

    /**
     * Gets all routines of a user
     * 
     * @param user the user
     * @return the routines
     */
    public  List<Routine> getRoutines(User user);

    /**
     * Gets a routine by its id
     * 
     * @param id the id
     * @param user the user
     * @return the routine
     */
    public Routine getRoutineByID(String id, User user);

    /**
     * Deletes a routine
     * 
     * @param id the id
     * @param user the user
     * @return true if the routine was deleted, false otherwise
     */
    public boolean deleteRoutine(String id, User user);

    /**
     * Switches a routine
     * 
     * @param id the id
     * @param state the state
     * @param user the user
     * @return true if the routine was switched, false otherwise
     */
    public boolean switchRoutine(String id, boolean state, User user);

    /**
     * Updates a routine
     * 
     * @param routine the routine
     * @param userID the user id
     */
    public void updateRoutine(Routine routine, int userID);
}
