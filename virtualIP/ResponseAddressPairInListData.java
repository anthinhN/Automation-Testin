package dto.vServerr.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddressPairInListData {
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("vipUuid")
    private String vipUuid;
    @JsonProperty("netInterfaceUuid")
    private String netInterfaceUuid;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("netInterfaceIp")
    private String netInterfaceIp;
}
