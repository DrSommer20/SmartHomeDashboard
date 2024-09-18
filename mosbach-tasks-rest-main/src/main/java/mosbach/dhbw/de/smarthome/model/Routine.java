package mosbach.dhbw.de.smarthome.model;

import java.util.List;

public class Routine {
;
    private String name;
    private List<Action> actions;
    private String triggerTime;
    private boolean state;

    public Routine( String name, List<Action> actions, String triggerTime, boolean state) {
        this.name = name;
        this.actions = actions;
        this.triggerTime = triggerTime;
        this.state = state;
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

    
}
