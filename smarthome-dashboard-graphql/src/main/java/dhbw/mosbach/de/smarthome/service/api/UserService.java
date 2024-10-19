package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.model.User;

public interface UserService {

    /**
     * Adds a user to the database
     * 
     * @param user the user
     */
    public void addUser(User user);

    /**
     * Gets a user by its email
     * 
     * @param email the email
     * @return the user
     */
    public User getUserByEmail(String email);

    /**
     * Deletes a user
     * 
     * @param email the email
     * @return true if the user was deleted, false otherwise
     */
    public boolean deleteUser(String email);

    /**
     * Gets a user by its token
     * 
     * @param token the token
     * @return the user
     */
    public User getUser(String token);

    /**
     * Gets a user by its id
     * 
     * @param userID the id
     * @return the user
     */
    public String getUserPATbyID(int userID);

    /**
     * Gets a user by its id
     * 
     * @param userID the id
     * @return the user
     */
    public User getUserById(int userID);

    /**
     * Updates a user
     * 
     * @param user the user
     * @return true if the user was updated, false otherwise
     */
    public boolean updateUser(User user);

    /**
     * Verifies a user
     * 
     * @param email the email
     * @return true if the user was verified, false otherwise
     */
    public boolean verifyUser(String email);
}
