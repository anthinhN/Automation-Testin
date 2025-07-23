package dto.vServerr.response.vbackup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBackupRestore {
    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private float userId;
    @JsonProperty("backendId")
    private String backendId;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("destinationServerId")
    private String destinationServerId;
    @JsonProperty("backupInstanceId")
    private String backupInstanceId;
    @JsonProperty("backupInstancePointId")
    private String backupInstancePointId;
    @JsonProperty("destinationVolumeId")
    private String destinationVolumeId;
    @JsonProperty("backupVolumePointId")
    private String backupVolumePointId;
    @JsonProperty("config")
    private String config;
    @JsonProperty("status")
    private String status;
    @JsonProperty("finishAt")
    private String finishAt;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("backupInstanceName")
    private String backupInstanceName;

}
