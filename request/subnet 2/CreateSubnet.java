package dto.vServerr.han01.request.subnet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSubnet {
    @JsonProperty("name")
    public String name;
    @JsonProperty("cidr")
    public String cidr;
}
