package mosbach.dhbw.de.smarthome.service;

import mosbach.dhbw.de.smarthome.model.Action;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class RoutineScheduler {
    private ScheduledFuture<?> scheduledTask;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    private final List<Action> actions;

    // Constructor to create the TaskScheduler instance
    public RoutineScheduler(List<Action> actions) {
        this.actions = actions;
    }

    // Method to schedule the daily routine execution at a specific time
    public void activateDailyRoutine(LocalTime routineTime) {
        // Cancel the previous task if already scheduled
        deactivateRoutine();

        // Calculate the initial delay and interval for scheduling
        long initialDelay = calculateInitialDelay(routineTime);
        long period = Duration.ofDays(1).toMillis(); // Daily repetition

        // Schedule the task to run daily
        scheduledTask = scheduledExecutorService.scheduleAtFixedRate(this::executeRoutine, initialDelay, period, TimeUnit.MILLISECONDS);

        System.out.println("Daily routine activated at: " + routineTime + "Initial delay: " + initialDelay + "Period: " + period);
    }

    // Method to deactivate the routine
    public void deactivateRoutine() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
            System.out.println("Routine deactivated.");
        }
    }

    private void executeRoutine() {

        System.out.println("Executing routine at: " + LocalDateTime.now());
        for (Action action : actions) {
            System.out.println("Device ID: " + action.getDeviceID() + ", Action: " + action.getAction());
            boolean response = SmartThings.setDeviceStatus(action.getAction(),action.getDeviceID(), "switch",action.getUser().getPat());
            System.out.println("Response: "+ response);
            if(response) {
                if(SmartThings.isSwitchOn(action.getDeviceID(), action.getUser().getPat())) DeviceService.getDeviceById(action.getDeviceID(), action.getUser()).setState("On");
                else DeviceService.getDeviceById(action.getDeviceID(), action.getUser()).setState("Off");
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
