package dto.vServerr.han01.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVIPData {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("networkName")
    private String networkName;
    @JsonProperty("networkCIDR")
    private String networkCIDR;
    @JsonProperty("subnetCIDR")
    private String subnetCIDR;
    @JsonProperty("subnetName")
    private String subnetName;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("networkUuid")
    private String networkUuid;
    @JsonProperty("subnetUuid")
    private String subnetUuid;
    @JsonProperty("ipAddress")
    private String ipAddress;
    @JsonProperty("addressPairIps")
    private ArrayList<Object> addressPairIps;
    @JsonProperty("createdAt")
    private Date createdAt;
}
