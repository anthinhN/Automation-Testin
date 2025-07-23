package dto.vServerr.response.subnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class DataSubnet {
    @JsonProperty("uuid")
    public String uuid;
    @JsonProperty("createdAt")
    public Date createdAt;
    @JsonProperty("updatedAt")
    public String updatedAt;
    @JsonProperty("deletedAt")
    public String deletedAt;
    @JsonProperty("status")
    public String status;
    @JsonProperty("cidr")
    public String cidr;
    @JsonProperty("networkUuid")
    public String networkUuid;
    @JsonProperty("routeTableUuid")
    public String routeTableUuid;
    @JsonProperty("name")
    public String name;
    @JsonProperty("interfaceAclPolicyUuid")
    public Object interfaceAclPolicyUuid;
}
