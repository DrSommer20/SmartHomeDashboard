
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
    "deviceScheme",
    "protocol"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("deviceScheme")
    private String deviceScheme;
    @JsonProperty("protocol")
    private String protocol;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param deviceScheme
     * @param protocol
     */
    public Data(String deviceScheme, String protocol) {
        super();
        this.deviceScheme = deviceScheme;
        this.protocol = protocol;
    }

    @JsonProperty("deviceScheme")
    public String getDeviceScheme() {
        return deviceScheme;
    }

    @JsonProperty("deviceScheme")
    public void setDeviceScheme(String deviceScheme) {
        this.deviceScheme = deviceScheme;
    }

    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
