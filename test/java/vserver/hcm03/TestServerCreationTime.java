package vserver.hcm03;

import data.vServerData;
import io.restassured.response.Response;
import service.vserver.hcm03.ServerMetricService;
import service.vserver.hcm03.ServerService;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import controller.vserver.hcm03.ServerController;

public class TestServerCreationTime {
    private String serverName;
    private int serverCreationTimeSeconds;

    @BeforeTest
    public void setUp() {
        serverName = ServerController.generateServerRandomName();
    }

    @Test(priority = 1)
    public void createServer() {
        Response response = ServerService.createServer(serverName);
        Assert.assertEquals(response.getStatusCode(), 202);
    }

    @Test(priority = 2)
    public void verifyServerCreationTime() {
        serverCreationTimeSeconds = ServerController.getServerCreationTimeSeconds(serverName);
        Assert.assertNotEquals(serverCreationTimeSeconds, vServerData.MAX_WAIT_TIME_SECONDS);
    }

    @Test(priority = 3)
    public void sendMetricOfServer() {
        String serverId = ServerController.getServerId(serverName);
        ServerMetricService.sendMetrics(serverId, serverCreationTimeSeconds);
        // Assert.assertEquals(serverName, serverName);
    }

    @Test(priority = 4)
    public void deleteServer() {
        String serverStatus = ServerController.getServerStatus(serverName);
        if (serverStatus.equals(vServerData.STATUS_ACTIVE)) {
            ServerService.deleteServer(serverName);
        }
    }
}
