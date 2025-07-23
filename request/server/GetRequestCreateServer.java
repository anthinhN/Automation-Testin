package dto.vServerr.request.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRequestCreateServer {
    @JsonProperty("resourceType")
    public String resourceType;
    @JsonProperty("action")
    public String action;
    @JsonProperty("resourceInfo")
    public ResourceInfo resourceInfo;
}
