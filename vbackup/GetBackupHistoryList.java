package dto.vServerr.response.vbackup;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBackupHistoryList {
    @JsonProperty("items")
    ArrayList <DataBackupHistory> items;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("totalPages")
    private Integer totalPages;
    @JsonProperty("totalItems")
    private Integer totalItems;

}
