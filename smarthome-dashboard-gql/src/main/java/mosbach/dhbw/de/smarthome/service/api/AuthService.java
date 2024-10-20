package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.model.User;

public interface AuthService {

    /**
     * Extracts the email from the token
     * 
     * @param token the token
     * @return the email
     */
    public String extractEmail(String token);

    /**
     * Checks if the token is expired
     * 
     * @param token the token
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token);

    /**
     * Generates a verification token
     * 
     * @param user the user
     * @return the token
     */
    public String generateVerificationToken(User user);

    /**
     * Generates a token
     * 
     * @param userDetails the user
     * @return the token
     */
    public String generateToken(User userDetails);

}