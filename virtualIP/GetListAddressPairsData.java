package dto.vServerr.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListAddressPairsData {
    @JsonProperty("data")
    private ArrayList<ResponseAddressPairInListData> data;
}
