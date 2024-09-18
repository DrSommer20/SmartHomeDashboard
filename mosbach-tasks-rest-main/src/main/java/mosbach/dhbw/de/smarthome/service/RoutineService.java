package mosbach.dhbw.de.smarthome.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;

public class RoutineService {
    private static HashMap<User, HashSet<Routine>> routines = new HashMap<User, HashSet<Routine>>();

    public static void addRoutine(User user, Routine routine) {
        if (!routines.containsKey(user)) {
            routines.put(user, new HashSet<Routine>());
        }
        routines.get(user).add(routine);

        if(routine.isState()) {
            RoutineScheduler routineScheduler = new RoutineScheduler();
            routineScheduler.scheduleOrderExecution(LocalDateTime.of(0, 0, 0, 0, 0, 0));
        }
    }

    public static void removeRoutine(User user, Routine routine) {
        if (routines.containsKey(user)) {
            routines.get(user).remove(routine);
            if(routine.isState()) {
                //TODO: cancel scheduled task
            }
        }
    }


}

class RoutineScheduler {
    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledTask;

    // Constructor to create the TaskScheduler instance
    public RoutineScheduler() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        this.taskScheduler = new ConcurrentTaskScheduler(scheduledExecutorService);
    }

    // Method to schedule an order execution at a specific time
    public void scheduleOrderExecution(LocalDateTime scheduleTime) {
        // Cancel the previous task if already scheduled
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
        }

        // Convert LocalDateTime to Instant
        Instant executionInstant = scheduleTime.atZone(ZoneId.systemDefault()).toInstant();

        // Schedule the task using the new Instant-based schedule method
        scheduledTask = taskScheduler.schedule(this::executeOrder, executionInstant);
        System.out.println("Order execution scheduled for: " + scheduleTime);
    }

    // Simulating order execution logic
    private void executeOrder() {
        System.out.println("Executing order at: " + LocalDateTime.now());
        // Your order execution logic goes here
    }
}
