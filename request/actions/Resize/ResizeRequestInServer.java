package dto.vServerr.request.actions.Resize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResizeRequestInServer {
    @JsonProperty("resourceType")
    private String respurceType;
    @JsonProperty("action")
    private String action;
    @JsonProperty("resourceInfo")
    private ResourceInfo resourceInfo;
}
