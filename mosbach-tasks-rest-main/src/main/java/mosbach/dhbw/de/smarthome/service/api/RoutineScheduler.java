package mosbach.dhbw.de.smarthome.service.api;

import java.time.LocalTime;

public interface RoutineScheduler {
    public void activateDailyRoutine(LocalTime routineTime);
    public void deactivateDailyRoutine();
}
