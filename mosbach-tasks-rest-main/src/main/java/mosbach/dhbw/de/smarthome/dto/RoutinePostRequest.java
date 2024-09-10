
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoutinePostRequest {

    @JsonProperty("token")
    private String token;
    @JsonProperty("routine")
    private RoutineDTO routine;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoutinePostRequest() {
    }

    /**
     * 
     * @param routine
     * @param token
     */
    public RoutinePostRequest(String token, RoutineDTO routine) {
        super();
        this.token = token;
        this.routine = routine;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("routine")
    public RoutineDTO getRoutine() {
        return routine;
    }

    @JsonProperty("routine")
    public void setRoutine(RoutineDTO routine) {
        this.routine = routine;
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
