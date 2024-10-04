package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;

public interface RoutineService {
    public void addRoutine(User user, Routine routine);
    public  List<Routine> getRoutines(User user);
    public Routine getRoutineByID(String id, User user);
    public boolean deleteRoutine(String id, User user);
}
