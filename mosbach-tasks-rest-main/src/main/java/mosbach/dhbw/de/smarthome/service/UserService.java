package mosbach.dhbw.de.smarthome.service;

import java.util.ArrayList;
import java.util.List;


import mosbach.dhbw.de.smarthome.model.User;

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


        public static void updateUser(User oldUser, String field, String value) {
            switch (field) {
                case "firstName"-> oldUser.setFirstName(value);
                case "lastName"-> oldUser.setLastName(value);
                case "email" -> oldUser.setEmail(value);
                case "passwort" -> oldUser.setPasswort(value);
            }
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
