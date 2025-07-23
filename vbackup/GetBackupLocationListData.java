package dto.vServerr.response.vbackup;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBackupLocationListData {
    @JsonProperty("items")
    ArrayList <DataBackupLocation> items;
    @JsonProperty("page")
    private float page;
    @JsonProperty("pageSize")
    private float pageSize;
    @JsonProperty("totalPages")
    private float totalPages;
    @JsonProperty("totalItems")
    private float totalItems;

}
