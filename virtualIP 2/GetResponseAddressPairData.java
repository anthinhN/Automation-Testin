package dto.vServerr.han01.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.response.virtualIP.ResponseAddressPairData;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseAddressPairData {
    @JsonProperty("data")
    private ResponseAddressPairData data;
}
