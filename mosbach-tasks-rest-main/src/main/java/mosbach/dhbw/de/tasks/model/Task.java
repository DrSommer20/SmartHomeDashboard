
package mosbach.dhbw.de.tasks.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    @JsonProperty("module")
    private String module;
    @JsonProperty("type")
    private String type;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("date-as-number")
    private Integer dateAsNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Task() {
    }

    /**
     * 
     * @param dateAsNumber
     * @param module
     * @param grade
     * @param type
     */
    public Task(String module, String type, String grade, Integer dateAsNumber) {
        super();
        this.module = module;
        this.type = type;
        this.grade = grade;
        this.dateAsNumber = dateAsNumber;
    }

    @JsonProperty("module")
    public String getModule() {
        return module;
    }

    @JsonProperty("module")
    public void setModule(String module) {
        this.module = module;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("grade")
    public String getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty("date-as-number")
    public Integer getDateAsNumber() {
        return dateAsNumber;
    }

    @JsonProperty("date-as-number")
    public void setDateAsNumber(Integer dateAsNumber) {
        this.dateAsNumber = dateAsNumber;
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
