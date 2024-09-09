package mosbach.dhbw.de.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import mosbach.dhbw.de.smarthome.model.User;

public class UserService {
        private static List<User> users = new ArrayList<>();

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


        public static boolean updateUser(String email, User updatedUser) {
            User existingUser = getUserByEmail(email);
            if (existingUser != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setPasswort(updatedUser.getPasswort());
                existingUser.setEmail(updatedUser.getEmail());
                return true;
            }
            return false;
        }

        public static boolean deleteUser(String email) {
            User existingUser = getUserByEmail(email);
            if (existingUser != null) {
                users.remove(existingUser);
                return true;
            }
            return false;
        }
}
