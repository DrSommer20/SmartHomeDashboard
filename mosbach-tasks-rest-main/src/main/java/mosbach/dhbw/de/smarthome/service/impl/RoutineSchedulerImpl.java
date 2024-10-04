package mosbach.dhbw.de.smarthome.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.model.Action;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;
import mosbach.dhbw.de.smarthome.service.api.RoutineScheduler;
import mosbach.dhbw.de.smarthome.service.api.SmartThings;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@Service
public class RoutineSchedulerImpl implements RoutineScheduler {
    private ScheduledFuture<?> scheduledTask;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    private final List<Action> actions;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private SmartThings smartThings;

    // Constructor to create the TaskScheduler instance
    public RoutineSchedulerImpl(List<Action> actions) {
        this.actions = actions;
    }

    // Method to schedule the daily routine execution at a specific time
    public void activateDailyRoutine(LocalTime routineTime) {
        // Cancel the previous task if already scheduled
        deactivateDailyRoutine();

        // Calculate the initial delay and interval for scheduling
        long initialDelay = calculateInitialDelay(routineTime);
        long period = Duration.ofDays(1).toMillis(); // Daily repetition

        // Schedule the task to run daily
        scheduledTask = scheduledExecutorService.scheduleAtFixedRate(this::executeRoutine, initialDelay, period, TimeUnit.MILLISECONDS);

        System.out.println("Daily routine activated at: " + routineTime + "Initial delay: " + initialDelay + "Period: " + period);
    }

    // Method to deactivate the routine
    public void deactivateDailyRoutine() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
            System.out.println("Routine deactivated.");
        }
    }

    private void executeRoutine() {

        System.out.println("Executing routine at: " + LocalDateTime.now());
        for (Action action : actions) {
            System.out.println("Device ID: " + action.getDeviceID() + ", Action: " + action.getAction() + ", User-PAT: " + userService.getUserPATbyID(action.getUserID()));
            boolean response = smartThings.setDeviceStatus(action.getAction(),action.getDeviceID(), "switch", userService.getUserPATbyID(action.getUserID()));
            System.out.println("Response: "+ response);
            if(response) {
                if(smartThings.isSwitchOn(action.getDeviceID(),userService.getUserPATbyID(action.getUserID()))) deviceService.getDeviceById(action.getDeviceID(), action.getUserID()).setState("On");
                else deviceService.getDeviceById(action.getDeviceID(), action.getUserID()).setState("Off");
            }
            else {
                System.out.println("Failed to execute action.");
            }
        }
    }

    // Helper method to calculate the initial delay until the next occurrence of the routine
    private long calculateInitialDelay(LocalTime routineTime) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        LocalDateTime nextRunTime = now.with(routineTime);

        // If the scheduled time is before the current time, schedule it for the next day
        if (nextRunTime.isBefore(now)) {
            nextRunTime = nextRunTime.plusDays(1);
        }

        // Calculate the delay between now and the next run time
        return Duration.between(now, nextRunTime).toMillis();
    }
}
