package mosbach.dhbw.de.smarthome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;

import org.springframework.stereotype.Service;

@Service
public class RoutineServiceImpl implements RoutineService {
    private static HashMap<User, HashSet<Routine>> routines = new HashMap<User, HashSet<Routine>>();

    public void addRoutine(User user, Routine routine) {
        if (!routines.containsKey(user)) {
            routines.put(user, new HashSet<Routine>());
        }
        routines.get(user).add(routine);
    }

    public  List<Routine> getRoutines(User user) {
        if (routines.containsKey(user)) {
            return new ArrayList<Routine>(routines.get(user));
        }
        return null;
    }

    public Routine getRoutineByID(String id, User user) {
        if (routines.containsKey(user)) {
            for (Routine routine : routines.get(user)) {
                if (routine.getID().equals(id)) {
                    return routine;
                }
            }
        }
        return null;
    }

    public boolean deleteRoutine(String id, User user) {
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

    @Override
    public boolean switchRoutine(String id, boolean state, User user) {
        Routine routine = getRoutineByID(id, user);
        if (routine != null) {
            routine.setState(state);
            return true;
        }
        return false;
    }


}

