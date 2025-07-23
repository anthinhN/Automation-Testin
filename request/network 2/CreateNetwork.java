package dto.vServerr.han01.request.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNetwork {
    @JsonProperty("name")
    public String name;
    @JsonProperty("cidr")
    public String cidr;
    @JsonProperty("tags")
    private ArrayList<String> tags;
}
