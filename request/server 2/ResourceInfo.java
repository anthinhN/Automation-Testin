package dto.vServerr.han01.request.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceInfo {
    @JsonProperty("isPoc")
    private Boolean isPoc;
    @JsonProperty("isEnableAutoRenew")
    private Boolean isEnableAutoRenew;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rootDiskSize")
    private Integer rootDiskSize;
    @JsonProperty("rootDiskEncryptionType")
    private String rootDiskEncryptionType;
    @JsonProperty("rootDiskTypeId")
    private String rootDiskTypeId;
    @JsonProperty("dataDiskSize")
    private String dataDiskSize;
    @JsonProperty("dataDiskEncryptionType")
    private String dataDiskEncryptionType;
    @JsonProperty("dataDiskTypeId")
    private String dataDiskTypeId;
    @JsonProperty("dataDiskName")
    private String dataDiskName;
    @JsonProperty("attachFloating")
    private Boolean attachFloating;
    @JsonProperty("encryptionVolume")
    private Boolean encryptionVolume;
    @JsonProperty("expirePassword")
    private Boolean expirePassword;
    @JsonProperty("imageId")
    private String imageId;
    @JsonProperty("osLicence")
    private Boolean osLicence;
    @JsonProperty("securityGroup")
    private List<String> securityGroup;
    @JsonProperty("sshKeyId")
    private String sshKeyId;
    @JsonProperty("serverGroupId")
    private String serverGroupId;
    @JsonProperty("flavorId")
    private String flavorId;
    @JsonProperty("networkId")
    private String networkId;
    @JsonProperty("subnetId")
    private String subnetId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userPassword")
    private String userPassword;
    @JsonProperty("period")
    private Integer period;
}
