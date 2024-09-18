package mosbach.dhbw.de.smarthome.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import mosbach.dhbw.de.smarthome.service.DeviceService;
import mosbach.dhbw.de.smarthome.service.SmartThings;

public class Routine {

    private static int idCounter = 0;
    private String id;
    private String name;
    private List<Action> actions;
    private String triggerTime;
    private boolean state;
    private RoutineScheduler routineScheduler;

    public Routine(String id, String name, List<Action> actions, String triggerTime, boolean state) {
        this.id = id;
        this.name = name;
        this.actions = actions;
        this.triggerTime = triggerTime;
        this.state = state;
        if(state) {
            activateRoutine();
        }
    }

    public Routine(String name, List<Action> actions, String triggerTime, boolean state) {
        this(idCounter++ + "", name, actions, triggerTime, state);
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if(state) {
            activateRoutine();
        }
        else {
            deactivateRoutine();
        }
    }

    public void activateRoutine() {
        LocalTime routineTime = LocalTime.parse(triggerTime);
        this.routineScheduler = new RoutineScheduler(actions);
        this.routineScheduler.activateDailyRoutine(routineTime);
    }

    public void deactivateRoutine() {
        this.routineScheduler.deactivateRoutine();
    }
}


class RoutineScheduler {
    //private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledTask;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private List<Action> actions;

    // Constructor to create the TaskScheduler instance
    public RoutineScheduler(List<Action> actions) {
        this.actions = actions;
        //this.taskScheduler = new ConcurrentTaskScheduler(scheduledExecutorService);
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
        
        System.out.println("Daily routine activated at: " + routineTime);
    }

    // Method to deactivate the routine
    public void deactivateRoutine() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
            System.out.println("Routine deactivated.");
        }
    }

    // Simulating routine execution logic
    private void executeRoutine() {
        
        System.out.println("Executing routine at: " + LocalDateTime.now());
        for (Action action : actions) {
            System.out.println("Device ID: " + action.getDeviceID() + ", Action: " + action.getAction());
            if(SmartThings.setDeviceStatus(action.getAction(),action.getDeviceID(), "switch",action.getUser().getPat())) {
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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRunTime = now.with(routineTime);

        // If the scheduled time is before the current time, schedule it for the next day
        if (nextRunTime.isBefore(now)) {
            nextRunTime = nextRunTime.plusDays(1);
        }

        // Calculate the delay between now and the next run time
        return Duration.between(now, nextRunTime).toMillis();
    }
}
