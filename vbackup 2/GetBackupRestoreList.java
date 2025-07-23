package dto.vServerr.han01.response.vbackup;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.response.vbackup.DataBackupRestore;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBackupRestoreList {
    @JsonProperty("items")
    ArrayList<dto.vServerr.response.vbackup.DataBackupRestore> items = new ArrayList <DataBackupRestore> ();
    @JsonProperty("page")
    private float page;
    @JsonProperty("pageSize")
    private float pageSize;
    @JsonProperty("totalPages")
    private float totalPages;
    @JsonProperty("totalItems")
    private float totalItems;

}
