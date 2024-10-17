package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.List;
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
"devicetypes"
})
@Generated("jsonschema2pojo")
public class AllTypes {

@JsonProperty("devicetypes")
private List<DeviceType> deviceTypes;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public AllTypes() {
}

public AllTypes(List<DeviceType> deviceTypes) {
super();
this.deviceTypes = deviceTypes;
}

@JsonProperty("devicetypes")
public List<DeviceType> getDeviceTypes() {
return deviceTypes;
}

@JsonProperty("devicetypes")
public void setDeviceTypes(List<DeviceType> deviceTypes) {
this.deviceTypes = deviceTypes;
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