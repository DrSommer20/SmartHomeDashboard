
package mosbach.dhbw.de.smarthome.dto.smartthings;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deviceId",
    "name",
    "label",
    "manufacturerName",
    "presentationId",
    "deviceManufacturerCode",
    "locationId",
    "ownerId",
    "roomId",
    "components",
    "createTime",
    "profile",
    "viper",
    "type",
    "restrictionTier",
    "allowed",
    "executionContext"
})
@Generated("jsonschema2pojo")
public class DeviceST {

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("label")
    private String label;
    @JsonProperty("manufacturerName")
    private String manufacturerName;
    @JsonProperty("presentationId")
    private String presentationId;
    @JsonProperty("deviceManufacturerCode")
    private String deviceManufacturerCode;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("ownerId")
    private String ownerId;
    @JsonProperty("roomId")
    private String roomId;
    @JsonProperty("components")
    private List<Component> components;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("profile")
    private Profile profile;
    @JsonProperty("viper")
    private Viper viper;
    @JsonProperty("type")
    private String type;
    @JsonProperty("restrictionTier")
    private Integer restrictionTier;
    @JsonProperty("allowed")
    private List<Object> allowed;
    @JsonProperty("executionContext")
    private String executionContext;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeviceST() {
    }

    /**
     * 
     * @param components
     * @param manufacturerName
     * @param allowed
     * @param profile
     * @param label
     * @param ownerId
     * @param type
     * @param deviceId
     * @param deviceManufacturerCode
     * @param roomId
     * @param presentationId
     * @param createTime
     * @param locationId
     * @param executionContext
     * @param name
     * @param viper
     * @param restrictionTier
     */
    public DeviceST(String deviceId, String name, String label, String manufacturerName, String presentationId, String deviceManufacturerCode, String locationId, String ownerId, String roomId, List<Component> components, String createTime, Profile profile, Viper viper, String type, Integer restrictionTier, List<Object> allowed, String executionContext) {
        super();
        this.deviceId = deviceId;
        this.name = name;
        this.label = label;
        this.manufacturerName = manufacturerName;
        this.presentationId = presentationId;
        this.deviceManufacturerCode = deviceManufacturerCode;
        this.locationId = locationId;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.components = components;
        this.createTime = createTime;
        this.profile = profile;
        this.viper = viper;
        this.type = type;
        this.restrictionTier = restrictionTier;
        this.allowed = allowed;
        this.executionContext = executionContext;
    }

    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
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

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("manufacturerName")
    public String getManufacturerName() {
        return manufacturerName;
    }

    @JsonProperty("manufacturerName")
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @JsonProperty("presentationId")
    public String getPresentationId() {
        return presentationId;
    }

    @JsonProperty("presentationId")
    public void setPresentationId(String presentationId) {
        this.presentationId = presentationId;
    }

    @JsonProperty("deviceManufacturerCode")
    public String getDeviceManufacturerCode() {
        return deviceManufacturerCode;
    }

    @JsonProperty("deviceManufacturerCode")
    public void setDeviceManufacturerCode(String deviceManufacturerCode) {
        this.deviceManufacturerCode = deviceManufacturerCode;
    }

    @JsonProperty("locationId")
    public String getLocationId() {
        return locationId;
    }

    @JsonProperty("locationId")
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("ownerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("ownerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("roomId")
    public String getRoomId() {
        return roomId;
    }

    @JsonProperty("roomId")
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonProperty("components")
    public List<Component> getComponents() {
        return components;
    }

    @JsonProperty("components")
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @JsonProperty("createTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("createTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("profile")
    public Profile getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @JsonProperty("viper")
    public Viper getViper() {
        return viper;
    }

    @JsonProperty("viper")
    public void setViper(Viper viper) {
        this.viper = viper;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("restrictionTier")
    public Integer getRestrictionTier() {
        return restrictionTier;
    }

    @JsonProperty("restrictionTier")
    public void setRestrictionTier(Integer restrictionTier) {
        this.restrictionTier = restrictionTier;
    }

    @JsonProperty("allowed")
    public List<Object> getAllowed() {
        return allowed;
    }

    @JsonProperty("allowed")
    public void setAllowed(List<Object> allowed) {
        this.allowed = allowed;
    }

    @JsonProperty("executionContext")
    public String getExecutionContext() {
        return executionContext;
    }

    @JsonProperty("executionContext")
    public void setExecutionContext(String executionContext) {
        this.executionContext = executionContext;
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
