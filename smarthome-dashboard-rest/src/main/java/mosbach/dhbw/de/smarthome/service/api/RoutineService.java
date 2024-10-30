package mosbach.dhbw.de.smarthome.service.api;

import java.io.IOException;

import mosbach.dhbw.de.smarthome.dto.AllRoutines;
import mosbach.dhbw.de.smarthome.dto.RoutineDTO;

public interface RoutineService {
    /**
     * Updates a routine
     * 
     * @param token the token
     * @param id the id
     * @param changeRequest the change request
     * @return true if the routine was updated, false otherwise
     * @throws IOException
     */
    public boolean updateRoutine(String token, int id, RoutineDTO changeRequest) throws IOException;

    /**
     * Gets a routine by id
     * 
     * @param token the token
     * @param id the id
     * @return the routine
     * @throws IOException
     */
    public RoutineDTO getRoutineById(String token, int id) throws IOException;

    /**
     * Deletes a routine
     * 
     * @param token the token
     * @param id the id
     * @return true if the routine was deleted, false otherwise
     * @throws IOException
     */
    public boolean deleteRoutine(String token, int id) throws IOException;

    /**
     * Gets all routines
     * 
     * @param token the token
     * @return all routines
     * @throws IOException
     */
    public AllRoutines getAllRoutines(String token) throws IOException;

    /**
     * Creates a routine
     * 
     * @param token the token
     * @param routine the routine
     * @return true if the routine was created, false otherwise
     * @throws IOException
     */
    public boolean createRoutine(String token, RoutineDTO routine) throws IOException;

    /**
     * Switches a routine
     * 
     * @param token the token
     * @param id the id
     * @param state the state
     * @return true if the routine was switched, false otherwise
     * @throws IOException
     */
    public boolean switchRoutine(String token, int id, String state) throws IOException;
}
