
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {

    @JsonProperty("device_id")
    private int deviceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("typeID")
    private int typeID;
    @JsonProperty("type")
    private String type;
    @JsonProperty("typeIcon")
    private String typeIcon;
    @JsonProperty("location")
    private int location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeviceDTO() {
    }

    /**
     * 
     * @param deviceId
     * @param name
     * @param typeIcon
     * @param typeID
     * @param location
     * @param type
     */
    public DeviceDTO(int deviceId, String name, String type, int location, int typeID, String typeIcon) {
        super();
        this.deviceId = deviceId;
        this.name = name;
        this.type = type;
        this.typeID = typeID;
        this.typeIcon = typeIcon;
        this.location = location;
    }

    @JsonProperty("device_id")
    public int getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("location")
    public int getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(int location) {
        this.location = location;
    }

    @JsonProperty("typeID")
    public int getTypeID() {
        return typeID;
    }

    @JsonProperty("typeID")
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    @JsonProperty("typeIcon")
    public String getTypeIcon() {
        return typeIcon;
    }

    @JsonProperty("typeIcon")
    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
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
