package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Routine;

public interface RoutineService {

    /**
     * Adds a routine to the database
     * 
     * @param user the user
     * @param routine the routine
     */
    public void addRoutine(int userId, Routine routine);

    /**
     * Gets all routines of a user
     * 
     * @param user the user
     * @return the routines
     */
    public  List<Routine> getRoutines(int userId);

    /**
     * Gets a routine by its id
     * 
     * @param id the id
     * @param user the user
     * @return the routine
     */
    public Routine getRoutineByID(int id, int userId);

    /**
     * Deletes a routine
     * 
     * @param id the id
     * @param user the user
     * @return true if the routine was deleted, false otherwise
     */
    public boolean deleteRoutine(int id, int userId);

    /**
     * Switches a routine
     * 
     * @param id the id
     * @param state the state
     * @param user the user
     * @return true if the routine was switched, false otherwise
     */
    public boolean switchRoutine(int id, boolean state, int userId);

    /**
     * Updates a routine
     * 
     * @param routine the routine
     * @param userID the user id
     */
    public void updateRoutine(Routine routine, int userID);

    /**
     * Deletes all actions of a routine
     * 
     * @param routineID the routine id
     * @return true if the actions were deleted, false otherwise
     */
    public boolean deleteActionsByDevice(String deviceId);
}
