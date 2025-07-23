package dto.vServerr.request.securityGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCreateRuleData {
    @JsonProperty("securityGroupId")
    private String securityGroupId;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("etherType")
    private String etherType;
    @JsonProperty("portRangeMin")
    private int portRangeMin;
    @JsonProperty("portRangeMax")
    private int portRangeMax;
    @JsonProperty("remoteIpPrefix")
    private String remoteIpPrefix;
    @JsonProperty("description")
    private String description;
}
