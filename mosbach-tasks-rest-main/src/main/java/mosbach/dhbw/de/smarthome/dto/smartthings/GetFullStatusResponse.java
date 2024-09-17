
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
    "healthCheck",
    "refresh",
    "switch"
})
@Generated("jsonschema2pojo")
public class GetFullStatusResponse {

    @JsonProperty("healthCheck")
    private HealthCheck healthCheck;
    @JsonProperty("refresh")
    private Refresh refresh;
    @JsonProperty("switch")
    private Switch _switch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetFullStatusResponse() {
    }

    /**
     * 
     * @param healthCheck
     * @param _switch
     * @param refresh
     */
    public GetFullStatusResponse(HealthCheck healthCheck, Refresh refresh, Switch _switch) {
        super();
        this.healthCheck = healthCheck;
        this.refresh = refresh;
        this._switch = _switch;
    }

    @JsonProperty("healthCheck")
    public HealthCheck getHealthCheck() {
        return healthCheck;
    }

    @JsonProperty("healthCheck")
    public void setHealthCheck(HealthCheck healthCheck) {
        this.healthCheck = healthCheck;
    }

    @JsonProperty("refresh")
    public Refresh getRefresh() {
        return refresh;
    }

    @JsonProperty("refresh")
    public void setRefresh(Refresh refresh) {
        this.refresh = refresh;
    }

    @JsonProperty("switch")
    public Switch getSwitch() {
        return _switch;
    }

    @JsonProperty("switch")
    public void setSwitch(Switch _switch) {
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
