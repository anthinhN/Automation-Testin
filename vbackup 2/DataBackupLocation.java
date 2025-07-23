package dto.vServerr.han01.response.vbackup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import dto.vServerr.response.vbackup.Config;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBackupLocation {
    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private float userId;
    @JsonProperty("backendId")
    private String backendId;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("isDefault")
    private String isDefault;
    @JsonProperty("type")
    private String type;
    @JsonProperty("ConfigObject")
    private Config config;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

}


