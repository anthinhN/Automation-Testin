package vserver.han01.server;
import dto.vServerr.response.server.GetGeneralInformationServerData;
import dto.vServerr.response.server.ServerResponseData.Data;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.vserver.han01.ServerService;
import data.vserver.*;
//import dto.vserver.han01.response.ServerResponse.Data;
//import dto.vserver.han01.responsebody.generalinfo.GetGeneralInformationServerData;

public class TestViewGeneralInformation {
    private String serverId = "ins-e7c7ec6a-c7ae-4b3d-9760-3dc5e472d0c4";
    private String serverName = "thinhna-auto-no-delete";

    @Test(priority = 1)
    public void verifyServerGeneralInformation() {
        // Get the general information of the server
        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        // Ensure serverData is not null
        Assert.assertNotNull(serverData, "Server data should not be null");

        // Verify server general information
        // - ID instance type
        Assert.assertEquals(serverData.getUuid(), serverId, "Server ID should match");
        //server name
        Assert.assertEquals(serverData.getName(), serverName, "Server name should match");
        //server created date
        Assert.assertEquals(serverData.getCreatedAt().toString(),"Fri Aug 02 10:17:46 ICT 2024","Created date should match"); //change this one
        //server status
        Assert.assertEquals(serverData.getStatus(), "STOPPED", "Server status should be ACTIVE");
        // - OS Image type
        Assert.assertEquals(serverData.getImage().getImageType(), vServerData.OSImageType,"OS should match");
        // - OS Version
        Assert.assertEquals(serverData.getImage().getImageVersion(),vServerData.OSImageVersion,"OS version should matcb");
        // - OS Licence
        Assert.assertEquals(serverData.isLicence(), false, "OS Licence should be true");
        // - Memory
        Assert.assertEquals(serverData.getFlavor().getMemory(), 1,"Memory should not be null");
        // - CPU
        Assert.assertEquals(serverData.getFlavor().getCpu(), 1,"CPU should not be null");
        // - SSH key
        Assert.assertEquals(serverData.getSshKeyName(), null, "SSH key should be false or No");
        // - Encryption volume
        Assert.assertEquals(serverData.isEncryptionVolume(), false,"Encryption volume should be true");
        // - Server group
        Assert.assertNull(serverData.getServerGroupName(), "Server group should be null");
    }
}
