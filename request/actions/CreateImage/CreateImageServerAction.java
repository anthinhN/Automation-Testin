package dto.vServerr.request.actions.CreateImage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateImageServerAction {
    @JsonProperty("name")
    private String name;
    @JsonProperty("tags")
    private ArrayList<String> tags;
}
