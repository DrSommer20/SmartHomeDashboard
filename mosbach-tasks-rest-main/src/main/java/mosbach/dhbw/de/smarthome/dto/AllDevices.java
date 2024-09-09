
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
public class AllDevices {

    @JsonProperty("devices")
    private List<DeviceGetResponse> devices;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllDevices() {
    }

    /**
     * 
     * @param deviceGetResponse
     */
    public AllDevices(List<DeviceGetResponse> devicesGetResponses) {
        super();
        this.devices = devicesGetResponses;
    }

    @JsonProperty("devices")
    public List<DeviceGetResponse> getDevices() {
        return devices;
    }

    @JsonProperty("devices")
    public void setDevices(List<DeviceGetResponse> devices) {
        this.devices = devices;
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
