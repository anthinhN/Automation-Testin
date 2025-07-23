package dto.vServerr.request.actions.EnableBackUp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnableBackUpRequestAction {
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("serverIds")
    private List<String> serverIds;
}
