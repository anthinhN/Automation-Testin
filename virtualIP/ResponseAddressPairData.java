package dto.vServerr.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddressPairData {
    @JsonProperty("id")
    private int id;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("vipUuid")
    private String vipUuid;
    @JsonProperty("netInterfaceUuid")
    private String netInterfaceUuid;
    @JsonProperty("projectUuid")
    private String projectUuid;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("deletedAt")
    private Object deletedAt;
}
