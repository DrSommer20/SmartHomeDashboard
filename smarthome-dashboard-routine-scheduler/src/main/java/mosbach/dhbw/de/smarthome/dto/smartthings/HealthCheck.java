
package mosbach.dhbw.de.smarthome.dto.smartthings;

import java.util.LinkedHashMap;
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
    "checkInterval",
    "healthStatus",
    "DeviceWatch-Enroll",
    "DeviceWatch-DeviceStatus"
})
@Generated("jsonschema2pojo")
public class HealthCheck {

    @JsonProperty("checkInterval")
    private CheckInterval checkInterval;
    @JsonProperty("healthStatus")
    private HealthStatus healthStatus;
    @JsonProperty("DeviceWatch-Enroll")
    private DeviceWatchEnroll deviceWatchEnroll;
    @JsonProperty("DeviceWatch-DeviceStatus")
    private DeviceWatchDeviceStatus deviceWatchDeviceStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public HealthCheck() {
    }

    /**
     * 
     * @param checkInterval
     * @param healthStatus
     * @param deviceWatchDeviceStatus
     * @param deviceWatchEnroll
     */
    public HealthCheck(CheckInterval checkInterval, HealthStatus healthStatus, DeviceWatchEnroll deviceWatchEnroll, DeviceWatchDeviceStatus deviceWatchDeviceStatus) {
        super();
        this.checkInterval = checkInterval;
        this.healthStatus = healthStatus;
        this.deviceWatchEnroll = deviceWatchEnroll;
        this.deviceWatchDeviceStatus = deviceWatchDeviceStatus;
    }

    @JsonProperty("checkInterval")
    public CheckInterval getCheckInterval() {
        return checkInterval;
    }

    @JsonProperty("checkInterval")
    public void setCheckInterval(CheckInterval checkInterval) {
        this.checkInterval = checkInterval;
    }

    @JsonProperty("healthStatus")
    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    @JsonProperty("healthStatus")
    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    @JsonProperty("DeviceWatch-Enroll")
    public DeviceWatchEnroll getDeviceWatchEnroll() {
        return deviceWatchEnroll;
    }

    @JsonProperty("DeviceWatch-Enroll")
    public void setDeviceWatchEnroll(DeviceWatchEnroll deviceWatchEnroll) {
        this.deviceWatchEnroll = deviceWatchEnroll;
    }

    @JsonProperty("DeviceWatch-DeviceStatus")
    public DeviceWatchDeviceStatus getDeviceWatchDeviceStatus() {
        return deviceWatchDeviceStatus;
    }

    @JsonProperty("DeviceWatch-DeviceStatus")
    public void setDeviceWatchDeviceStatus(DeviceWatchDeviceStatus deviceWatchDeviceStatus) {
        this.deviceWatchDeviceStatus = deviceWatchDeviceStatus;
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
