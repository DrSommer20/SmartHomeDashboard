package mosbach.dhbw.de.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import mosbach.dhbw.de.smarthome.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        private static List<User> users = new ArrayList<>();

        static{
            users.add(new User("Max", "Mustermann", "max@mustermann.de", "1234"));
        }

        public static void addUser(User user) {
            users.add(user);
        }

        public static User getUserByEmail(String email) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        }

        public static boolean deleteUser(String email) {
            User existingUser = getUserByEmail(email);
            if (existingUser != null) {
                users.remove(existingUser);
                return true;
            }
            return false;
        }

        static List<User> getAllUsers() {
            return users;
        }
}
