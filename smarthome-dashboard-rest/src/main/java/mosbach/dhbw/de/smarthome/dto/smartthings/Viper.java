
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
    "uniqueIdentifier",
    "manufacturerName",
    "modelName",
    "endpointAppId"
})
@Generated("jsonschema2pojo")
public class Viper {

    @JsonProperty("uniqueIdentifier")
    private String uniqueIdentifier;
    @JsonProperty("manufacturerName")
    private String manufacturerName;
    @JsonProperty("modelName")
    private String modelName;
    @JsonProperty("endpointAppId")
    private String endpointAppId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Viper() {
    }

    /**
     * 
     * @param modelName
     * @param manufacturerName
     * @param endpointAppId
     * @param uniqueIdentifier
     */
    public Viper(String uniqueIdentifier, String manufacturerName, String modelName, String endpointAppId) {
        super();
        this.uniqueIdentifier = uniqueIdentifier;
        this.manufacturerName = manufacturerName;
        this.modelName = modelName;
        this.endpointAppId = endpointAppId;
    }

    @JsonProperty("uniqueIdentifier")
    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    @JsonProperty("uniqueIdentifier")
    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    @JsonProperty("manufacturerName")
    public String getManufacturerName() {
        return manufacturerName;
    }

    @JsonProperty("manufacturerName")
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @JsonProperty("modelName")
    public String getModelName() {
        return modelName;
    }

    @JsonProperty("modelName")
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @JsonProperty("endpointAppId")
    public String getEndpointAppId() {
        return endpointAppId;
    }

    @JsonProperty("endpointAppId")
    public void setEndpointAppId(String endpointAppId) {
        this.endpointAppId = endpointAppId;
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
