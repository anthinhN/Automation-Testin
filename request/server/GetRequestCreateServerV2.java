package dto.vServerr.request.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRequestCreateServerV2 {
    @JsonProperty("isBuyMorePoc")
    private boolean isBuyMorePoc;

    @JsonProperty("isPoc")
    private boolean isPoc;

    @JsonProperty("isEnableAutoRenew")
    private boolean isEnableAutoRenew;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rootDiskSize")
    private int rootDiskSize;

    @JsonProperty("rootDiskEncryptionType")
    private Object rootDiskEncryptionType;

    @JsonProperty("rootDiskTypeId")
    private String rootDiskTypeId;

    @JsonProperty("dataDiskSize")
    private Object dataDiskSize;

    @JsonProperty("dataDiskEncryptionType")
    private Object dataDiskEncryptionType;

    @JsonProperty("dataDiskTypeId")
    private Object dataDiskTypeId;

    @JsonProperty("dataDiskName")
    private Object dataDiskName;

    @JsonProperty("attachFloating")
    private boolean attachFloating;

    @JsonProperty("encryptionVolume")
    private boolean encryptionVolume;

    @JsonProperty("expirePassword")
    private boolean expirePassword;

    @JsonProperty("imageId")
    private String imageId;

    @JsonProperty("osLicence")
    private boolean osLicence;

    @JsonProperty("securityGroup")
    private List<String> securityGroup;

    @JsonProperty("sshKeyId")
    private Object sshKeyId;

    @JsonProperty("serverGroupId")
    private Object serverGroupId;

    @JsonProperty("flavorId")
    private String flavorId;

    @JsonProperty("hostGroupId")
    private String hostGroupId;

    @JsonProperty("networkId")
    private String networkId;

    @JsonProperty("subnetId")
    private String subnetId;

    @JsonProperty("userName")
    private Object userName;

    @JsonProperty("userPassword")
    private Object userPassword;

    @JsonProperty("enableBackup")
    private boolean enableBackup;

    @JsonProperty("backupInstancePointId")
    private Object backupInstancePointId;

    @JsonProperty("createdFrom")
    private String createdFrom;

    @JsonProperty("tags")
    private List<Object> tags;
}
