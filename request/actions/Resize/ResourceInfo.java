package dto.vServerr.request.actions.Resize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceInfo {
    @JsonProperty("serverId")
    private String serverId;
    @JsonProperty("flavorId")
    private String flavorId;
    @JsonProperty("hostGroupId")
    private String hostGroupId;
}
