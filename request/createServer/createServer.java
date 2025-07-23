package dto.vServerr.request.createServer;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class createServer
{
    private boolean isBuyMorePoc;
    private boolean isPoc;
    private boolean isEnableAutoRenew;
    private String name;
    private int rootDiskSize;
    private String rootDiskEncryptionType;
    private String rootDiskTypeId;
    private Integer dataDiskSize;
    private String dataDiskEncryptionType;
    private String dataDiskTypeId;
    private String dataDiskName;
    private boolean attachFloating;
    private boolean encryptionVolume;
    private boolean expirePassword;
    private String imageId;
    private boolean osLicence;
    private ArrayList<String> securityGroup;
    private String sshKeyId;
    private String serverGroupId;
    private String flavorId;
    private String hostGroupId;
    private String networkId;
    private String subnetId;
    private String userName;
    private String userPassword;
    private boolean enableBackup;
    private String backupInstancePointId;
    private String snapshotInstancePointId;
    private String createdFrom;
    private ArrayList<String> tags;
    private ArrayList<String> configVolumeRestores;
    private String userData;
    private boolean userDataBase64Encoded;

}
