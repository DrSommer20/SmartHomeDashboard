package mosbach.dhbw.de.smarthome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;

public class RoutineService {
    private static HashMap<User, HashSet<Routine>> routines = new HashMap<User, HashSet<Routine>>();

    public static void addRoutine(User user, Routine routine) {
        if (!routines.containsKey(user)) {
            routines.put(user, new HashSet<Routine>());
        }
        routines.get(user).add(routine);
    }
    public static List<Routine> getRoutines(User user) {
        if (routines.containsKey(user)) {
            return new ArrayList<Routine>(routines.get(user));
        }
        return null;
    }

    public static Routine getRoutineByID(String id, User user) {
        if (routines.containsKey(user)) {
            for (Routine routine : routines.get(user)) {
                if (routine.getID().equals(id)) {
                    return routine;
                }
            }
        }
        return null;
    }

    public static boolean deleteRoutine(String id, User user) {
        Routine routine = getRoutineByID(id, user);
        if (routine != null) {
            routines.get(user).remove(routine);
            if(routine.isState()) {
                routine.deactivateRoutine();
            }
            return true;
        }
        return false;
    }


}

