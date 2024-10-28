
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import mosbach.dhbw.de.smarthome.model.Action;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ActionDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("device_name")
    private String deviceName;
    @JsonProperty("action")
    private String action;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ActionDTO() {
    }

    /**
     * 
     * @param action
     * @param deviceId
     */
    public ActionDTO(int id, String deviceId, String deviceName, String action) {
        super();
        this.id = id;
        this.deviceId = deviceId;
        this.action = action;
        this.deviceName = deviceName;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("device_name")
    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty("device_name")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public static List<ActionDTO> convertToDTO(List<Action> actions) {
        List<ActionDTO> actionDTOs = null;
        if (actions != null) {
            actionDTOs = new ArrayList<ActionDTO>();
            for (Action action : actions) {
                actionDTOs.add(new ActionDTO(action.getId(), action.getDeviceID(), action.getDeviceName(), action.getSetTo()));
            }
        }
        return actionDTOs;
    }
    public static List<Action> convertToModel(List<ActionDTO> actions, int userId) {
        List<Action> actionModels = null;
        if (actions != null) {
            actionModels = new ArrayList<Action>();
            for (ActionDTO actionDTO : actions) {
                actionModels.add(new Action(actionDTO.getId(), actionDTO.getDeviceId(), actionDTO.getDeviceName(), actionDTO.getAction(), userId));
            }
        }
        return actionModels;
    }

}
