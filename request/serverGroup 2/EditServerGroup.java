package dto.vServerr.han01.request.serverGroup;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import lombok.Data;
        import lombok.Getter;
        import lombok.Setter;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditServerGroup {
    @JsonProperty("serverGroupId")
    public String serverGroupId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
}
