package mosbach.dhbw.de.smarthome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.AuthService;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static List<User> users = new ArrayList<>();

    @Autowired
    private AuthService authService;

    
    static{
        users.add(new User("Max", "Mustermann", "max@mustermann.de", "1234"));
        users.get(0).setVerified(true);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByEmail(String email) {
        
        return users
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteUser(String email) {
        User existingUser = getUserByEmail(email);
        if (existingUser != null) {
            users.remove(existingUser);
            return true;
        }
        return false;
    }

    public User getUser(String token){
        return getUserByEmail(authService.extractEmail(token));
    }

    public String getUserPATbyID(int userID){
        return users
                .stream()
                .filter(user -> user.getUserID() == userID)
                .findFirst()
                .map(User::getPat)
                .orElse(null);
    }

    public User getUserById(int userID){
        return users
                .stream()
                .filter(user -> user.getUserID() == userID)
                .findFirst()
                .orElse(null);
    }
}
