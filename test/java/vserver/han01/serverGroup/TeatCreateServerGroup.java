package vserver.han01.serverGroup;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.vserver.han01.*;

public class TeatCreateServerGroup {
    private String serverGroupName;
    private String serverGroupID;
    private String createdAt;
    private String servers;

    @BeforeTest
    public void setUp(){
        serverGroupName = "TestservG123";
    }

    @Test(priority = 1)
    public void createServerGroup(){
        Response response = ServerService.createServerGroup(serverGroupName);
        Assert.assertEquals(response.getStatusCode(),202);

        //
        serverGroupID = response.jsonPath().getString("id");
        createdAt = response.jsonPath().getString("createdAt");
        servers = response.jsonPath().getString("servers");
    }

    /*@Test(priority = 2)
    public void verifyServerGroupInformation(){
        ServerGroupData serverGroupDetails = ServerService.serverGroupData(serverGroupID);
        Assert.assertEquals(serverGroupDetails.uuid, serverGroupID, "Server ID should match" );
        Assert.assertEquals(serverGroupDetails.createdAt, createdAt, "Created Date should match");
        Assert.assertEquals(serverGroupDetails.servers, servers, "Servers should match");
    }*/

    @Test(priority = 2) //delete after created
    public void deleteServerGroup(){
        ServerService.deleteServerGroup(serverGroupID);
    }

}
