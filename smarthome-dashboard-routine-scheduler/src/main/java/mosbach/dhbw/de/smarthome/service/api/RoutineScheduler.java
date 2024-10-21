package mosbach.dhbw.de.smarthome.service.api;

import java.time.LocalTime;

public interface RoutineScheduler {

    /**
     * Activates the daily routine
     * 
     * @param routineTime the time the routine should be activated
     */
    public void activateDailyRoutine(LocalTime routineTime);

    /**
     * Deactivates the daily routine
     */
    public void deactivateDailyRoutine();
}
