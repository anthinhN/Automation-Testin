package vserver.han01.server;
//import controller.vserver.hcm03.ServerController;
/*
public class TestDeleteServer {
    private String serverName;
    private String serverId;

    @BeforeTest
    public void setup() {
        serverName = "TestDeleteServer123";
    }

    @Test(priority = 0)
    public void createServer() {
        serverName = ServerController.generateServerRandomName();
        Response createResponse = ServerService.createServer(serverName);
        Assert.assertEquals(createResponse.getStatusCode(), 202, "Expected status code 202, but received: " + createResponse.getStatusCode());

        serverId = createResponse.jsonPath().getString("data.uuid");
        Assert.assertNotNull(serverId, "Server ID should not be null");

        // Print serverId for debugging
        System.out.println("Server ID: " + serverId);
    }

    @Test(priority = 1, dependsOnMethods = "createServer")
    public void deleteServer() {
        waitForServerToBeActive(serverId);
        ServerService.deleteServer(serverName);

        // Verify server deletion by attempting to get server details
        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        Assert.assertEquals(serverData.getStatus(), "DELETING", "Expected DELETING STATUS, but got: " + serverData.getStatus());
        System.out.println("Server deleted successfully with ID: " + serverId);
    }

    private void waitForServerToBeActive(String serverId) {
        int maxRetries = 10; // maximum number of retries
        int retryInterval = 30; // interval between retries in seconds

        for (int i = 0; i < maxRetries; i++) {
            String serverStatus = ServerController.getServerStatus(serverName);
            if ("ACTIVE".equals(serverStatus)) {
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.fail("Server did not reach 'ACTIVE' status within the expected time");
    }
}
*/