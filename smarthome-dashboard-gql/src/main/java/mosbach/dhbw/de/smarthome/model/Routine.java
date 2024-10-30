package mosbach.dhbw.de.smarthome.model;

import java.util.List;

public class Routine {

    private int id;
    private String name;
    private List<Action> actions;
    private String triggerTime;
    private boolean state;

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
}

