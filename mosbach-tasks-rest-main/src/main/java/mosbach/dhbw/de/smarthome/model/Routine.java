package mosbach.dhbw.de.smarthome.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import mosbach.dhbw.de.smarthome.service.api.RoutineScheduler;
import mosbach.dhbw.de.smarthome.service.impl.RoutineSchedulerImpl;

@Entity
public class Routine {

    private static int idCounter = 0;
    @Id
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

    public void refresh(){
        this.routineScheduler.deactivateDailyRoutine();
        if(state)activateRoutine();
        else routineScheduler = null;

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

