package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.model.User;

public interface UserService {
    public void addUser(User user);
    public User getUserByEmail(String email);
    public boolean deleteUser(String email);
    public User getUser(String token);
    public String getUserPATbyID(int userID);
    public User getUserById(int userID);
}
