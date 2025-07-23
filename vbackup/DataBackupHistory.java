package dto.vServerr.response.vbackup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBackupHistory {
    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("backendId")
    private String backendId;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("backupInstanceId")
    private String backupInstanceId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("snapshotTime")
    private String snapshotTime;
    @JsonProperty("finishTime")
    private String finishTime;
    @JsonProperty("serverId")
    private String serverId;
    @JsonProperty("size")
    private String size;
    @JsonProperty("usage")
    private float usage;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("destinationId")
    private String destinationId;
    @JsonProperty("policyId")
    private String policyId;
    @JsonProperty("backupInstanceName")
    private String backupInstanceName;

}
