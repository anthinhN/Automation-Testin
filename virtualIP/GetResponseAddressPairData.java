package dto.vServerr.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseAddressPairData {
    @JsonProperty("data")
    private ResponseAddressPairData data;
}
