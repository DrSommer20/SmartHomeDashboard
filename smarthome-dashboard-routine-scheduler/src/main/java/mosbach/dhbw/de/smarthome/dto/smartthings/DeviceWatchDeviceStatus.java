
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
    "value",
    "data",
    "timestamp"
})
@Generated("jsonschema2pojo")
public class DeviceWatchDeviceStatus {

    @JsonProperty("value")
    private String value;
    @JsonProperty("data")
    private Data__1 data;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeviceWatchDeviceStatus() {
    }

    /**
     * 
     * @param data
     * @param value
     * @param timestamp
     */
    public DeviceWatchDeviceStatus(String value, Data__1 data, String timestamp) {
        super();
        this.value = value;
        this.data = data;
        this.timestamp = timestamp;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("data")
    public Data__1 getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data__1 data) {
        this.data = data;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
