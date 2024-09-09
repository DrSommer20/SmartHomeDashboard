
package mosbach.dhbw.de.smarthome.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceGetResponse {

    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("loaction")
    private String loaction;
    @JsonProperty("status")
    private String status;
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
    public DeviceGetResponse(String deviceId, String name, String type, String loaction, String status) {
        super();
        this.deviceId = deviceId;
        this.name = name;
        this.type = type;
        this.loaction = loaction;
        this.status = status;
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

    @JsonProperty("loaction")
    public String getLoaction() {
        return loaction;
    }

    @JsonProperty("loaction")
    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
