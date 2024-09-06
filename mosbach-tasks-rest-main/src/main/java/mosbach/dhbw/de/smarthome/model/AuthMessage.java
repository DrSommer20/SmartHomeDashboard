
package  mosbach.dhbw.de.smarthome.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class AuthMessage {

    @JsonProperty("email")
    private String email;
    @JsonProperty("passwort")
    private String passwort;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AuthMessage() {
    }

    /**
     * 
     * @param passwort
     * @param email
     */
    public AuthMessage(String email, String passwort) {
        super();
        this.email = email;
        this.passwort = passwort;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("passwort")
    public String getPasswort() {
        return passwort;
    }

    @JsonProperty("passwort")
    public void setPasswort(String passwort) {
        this.passwort = passwort;
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
