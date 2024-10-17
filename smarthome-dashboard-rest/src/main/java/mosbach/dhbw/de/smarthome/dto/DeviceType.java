package mosbach.dhbw.de.smarthome.dto;

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
"typeID",
"type",
"typeIcon"
})
@Generated("jsonschema2pojo")
public class DeviceType {

@JsonProperty("typeID")
private Integer typeID;
@JsonProperty("type")
private String type;
@JsonProperty("typeIcon")
private String typeIcon;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public DeviceType() {
}

public DeviceType(Integer typeID, String type, String typeIcon) {
super();
this.typeID = typeID;
this.type = type;
this.typeIcon = typeIcon;
}

@JsonProperty("typeID")
public Integer getTypeID() {
return typeID;
}

@JsonProperty("typeID")
public void setTypeID(Integer typeID) {
this.typeID = typeID;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("typeIcon")
public String getTypeIcon() {
return typeIcon;
}

@JsonProperty("typeIcon")
public void setTypeIcon(String typeIcon) {
this.typeIcon = typeIcon;
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