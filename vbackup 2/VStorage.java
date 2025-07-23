package dto.vServerr.han01.response.vbackup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VStorage {
    @JsonProperty("regionId")
    private String regionId;
    @JsonProperty("regionName")
    private String regionName;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("used")
    private float used;
    @JsonProperty("total")
    private float total;
    @JsonProperty("storageService")
    private String storageService;
    @JsonProperty("containerName")
    private String containerName;

}
