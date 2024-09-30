package mosbach.dhbw.de.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import mosbach.dhbw.de.smarthome.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
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
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        }

        public boolean deleteUser(String email) {
            User existingUser = getUserByEmail(email);
            if (existingUser != null) {
                users.remove(existingUser);
                return true;
            }
            return false;
        }

        List<User> getAllUsers() {
            return users;
        }

        public User getUser(String token){
            return getUserByEmail(authService.extractUsername(token));
        }

        public String getUserPATbyID(int userID){
            for(User user : users){
                if(user.getUserID() == userID) return user.getPat();
            }
            return null;
        }
}
