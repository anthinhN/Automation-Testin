package dto.vServerr.han01.response.virtualIP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.response.virtualIP.ResponseVIPData;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseVIPListData {
    @JsonProperty("listData")
    private ArrayList<ResponseVIPData> listData;
    @JsonProperty("page")
    private int page;
    @JsonProperty("pageSize")
    private int pageSize;
    @JsonProperty("totalPage")
    private int totalPage;
    @JsonProperty("totalItem")
    private int totalItem;
}
