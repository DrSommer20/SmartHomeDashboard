package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.model.User;

public interface AuthService {

    public String extractEmail(String token);
    public boolean isTokenExpired(String token);
    public String generateVerificationToken(User user);
    public void invalidateToken(String token);
    public String generateToken(User userDetails);

}