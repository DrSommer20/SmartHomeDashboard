
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

public class AllRooms {

    @JsonProperty("rooms")
    private List<RoomDTO> rooms;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllRooms() {
    }

    /**
     * 
     * @param rooms
     */
    public AllRooms(List<RoomDTO> rooms) {
        super();
        this.rooms = rooms;
    }

    @JsonProperty("rooms")
    public List<RoomDTO> getRooms() {
        return rooms;
    }

    @JsonProperty("rooms")
    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
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
