
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import mosbach.dhbw.de.smarthome.model.Device;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceGetResponse {

    @JsonProperty("device_id")
    private String deviceId;
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
    @JsonProperty("roomName")
    private String roomName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("state")
    private String state;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeviceGetResponse() {
    }

    /**
     * 
     * @param name
     * @param loaction
     * @param type
     * @param deviceId
     * @param status
     */
    public DeviceGetResponse(String deviceId, String name, String type, String typeIcon, int typeID, int location, String status, String state, String roomName) {
        super();
        this.deviceId = deviceId;
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
        this.state = state;
        this.roomName = roomName;
        this.typeID = typeID;
        this.typeIcon = typeIcon;
    }

    public DeviceGetResponse(Device device) {
        this.deviceId = ""+device.getId();
        this.name = device.getName();
        this.type = device.getType();
        this.location = device.getLocation();
        this.status = device.getStatus();
        this.state = device.getState();
        this.roomName = device.getRoomName();
        this.typeID = device.getTypeID();
        this.typeIcon = device.getTypeIcon();
    }



    public DeviceGetResponse(String deviceId2, String label) {
        this.deviceId = deviceId2;
        this.name = label;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(String deviceId) {
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

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
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

    @JsonProperty("roomName")
    public String getRoomName() {
        return roomName;
    }

    @JsonProperty("roomName")
    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
