package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.model.User;

public interface UserService {

    /**
     * Gets a user by its email
     * 
     * @param email the email
     * @return the user
     */
    public User getUserByEmail(String email);

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

}
