package dto.vServerr.han01.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.response.virtualIP.ResponseVIPData;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseVIPData {
    @JsonProperty("data")
    private ResponseVIPData data;
}
