package mosbach.dhbw.de.smarthome.service;

import java.util.HashMap;
import java.util.UUID;

import mosbach.dhbw.de.smarthome.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static HashMap<User, String> userMap = new HashMap<User, String>();

    public static String addUser(User user) {
        String token = UUID.randomUUID().toString();
        userMap.put(user, token);
        return token;
    }

    public static void removeUser(User user) {
        userMap.remove(user);
    }

    public static String getToken(User user) {
        return userMap.get(user);
    }

    public static User getUser(String token) {
        for (User user : userMap.keySet()) {
            if (userMap.get(user).equals(token)) {
                return user;
            }
        }
        return null;
    }

    public static boolean checkUser(User user) {
        return userMap.containsKey(user);
    }
}
