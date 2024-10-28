
package mosbach.dhbw.de.smarthome.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import mosbach.dhbw.de.smarthome.model.Routine;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllRoutines {

    @JsonProperty("routines")
    private List<RoutineDTO> routineDTOs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllRoutines() {
    }

    /**
     * 
     * @param routineDTOs
     */
    public AllRoutines(List<RoutineDTO> routineDTOs) {
        super();
        this.routineDTOs = routineDTOs;
    }

    @JsonProperty("routines")
    public List<RoutineDTO> getRoutines() {
        return routineDTOs;
    }

    @JsonProperty("devices")
    public void setRoutines(List<RoutineDTO> routineDTOs) {
        this.routineDTOs = routineDTOs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

      public static AllRoutines convertToDTO(List<Routine> routines) {
        if(routines == null) return new AllRoutines(null);
        List<RoutineDTO> routineDTOs = new ArrayList<>();
        for (Routine routine : routines) {
            RoutineDTO routineDTO = RoutineDTO.convertToDTO(routine);
            routineDTOs.add(routineDTO);
        }
        return new AllRoutines(routineDTOs);
    }

}
