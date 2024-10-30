package mosbach.dhbw.de.smarthome.model;

import java.time.LocalTime;
import java.util.List;


import mosbach.dhbw.de.smarthome.service.api.RoutineScheduler;
import mosbach.dhbw.de.smarthome.service.impl.RoutineSchedulerImpl;

public class Routine {

    private int id;
    private String name;
    private List<Action> actions;
    private String triggerTime;
    private boolean state;
    private RoutineScheduler routineScheduler;

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param actions
     * @param triggerTime
     * @param state
     */
    public Routine(int id, String name, List<Action> actions, String triggerTime, boolean state) {
        this.id = id;
        this.name = name;
        this.actions = actions;
        this.triggerTime = triggerTime;
        this.state = state;
        this.state = state;
    }

    public Routine(String name, List<Action> actions, String triggerTime, boolean state) {
        this(0, name, actions, triggerTime, state);
    }

    public Routine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    }

    public RoutineScheduler getRoutineScheduler() {
        return routineScheduler;
    }

    public void setRoutineScheduler(RoutineScheduler routineScheduler) {
        this.routineScheduler = routineScheduler;
    }

    public void activateRoutine() {
        LocalTime routineTime = LocalTime.parse(triggerTime);
        this.routineScheduler = new RoutineSchedulerImpl(actions);
        this.routineScheduler.activateDailyRoutine(routineTime);
    }

    public void deactivateRoutine() {
        this.routineScheduler.deactivateDailyRoutine();
    }
}