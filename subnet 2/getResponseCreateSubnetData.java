package dto.vServerr.han01.response.subnet;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.request.subnet.DataSubnet;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class getResponseCreateSubnetData {
    @JsonProperty("data")
    public DataSubnet data;
}

