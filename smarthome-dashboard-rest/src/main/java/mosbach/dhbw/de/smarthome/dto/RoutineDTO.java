
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoutineDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("actions")
    private List<ActionDTO> actions;
    @JsonProperty("trigger")
    private Trigger trigger;
    @JsonProperty("state")
    private boolean state;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoutineDTO() {
    }

    /**
     * 
     * @param name
     * @param trigger
     * @param actions
     */
    public RoutineDTO(int id, String name, List<ActionDTO> actions, Trigger trigger, boolean state) {
        super();
        this.id = id;
        this.name = name;
        this.actions = actions;
        this.trigger = trigger;
        this.state = state;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("actions")
    public List<ActionDTO> getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(List<ActionDTO> actions) {
        this.actions = actions;
    }

    @JsonProperty("trigger")
    public Trigger getTrigger() {
        return trigger;
    }

    @JsonProperty("trigger")
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @JsonProperty("state")
    public boolean isState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(boolean state) {
        this.state = state;
    }

    @JsonProperty("id")
    public int getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(int id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
