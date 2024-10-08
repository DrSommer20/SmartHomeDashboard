
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
    "switch"
})
@Generated("jsonschema2pojo")
public class Switch {

    @JsonProperty("switch")
    private Switch__1 _switch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Switch() {
    }

    /**
     * 
     * @param _switch
     */
    public Switch(Switch__1 _switch) {
        super();
        this._switch = _switch;
    }

    @JsonProperty("switch")
    public Switch__1 getSwitch() {
        return _switch;
    }

    @JsonProperty("switch")
    public void setSwitch(Switch__1 _switch) {
        this._switch = _switch;
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
