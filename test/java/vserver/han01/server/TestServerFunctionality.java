package vserver.han01.server;

import controller.vserver.han01.ServerController;
import controller.selenium.SeleniumController;
import dto.vServerr.response.server.GetGeneralInformationServerData;
import dto.vServerr.response.server.ServerResponseData.Data;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import service.vserver.han01.*;
import data.vserver.*;

public class TestServerFunctionality {
    private String serverName;
    private String serverId;
    private static final String projectId = "pro-bbe49463-5fb8-4701-98e6-115010032b1b";
    private String networkName = "TestNetwork123";
    private String networkID;
    private String networkStatus;
    private String subnetID, subnetStatus, subnetName;
   /* @Test(priority = 0)
    public void CreateNetwork(){

        Response response = ServerService.createNetwork(networkName);
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200, but received: " + response.getStatusCode());

        networkStatus = response.jsonPath().getString("data.status");
        networkID = response.jsonPath().getString("data.id");
        Assert.assertEquals(networkStatus, "CREATING", "Network status should be matching");

        // Check the network status after the delay
        System.out.println("network status now is " + networkStatus);
    }*/

   /* @Test(priority = 1, dependsOnMethods = "CreateNetwork")
    public void createSubnet(){
        // Introduce a delay before checking the network status
        waitForNetworkToBeActive(networkName);

        Response createResponse = ServerService.createSubnet(networkID);

        Assert.assertEquals(createResponse.getStatusCode(), 200,
                "Expected status code 200, but received: " + createResponse.getStatusCode());

        subnetStatus = createResponse.jsonPath().getString("data.status");
        subnetID = createResponse.jsonPath().getString("data.uuid");
        subnetName = createResponse.jsonPath().getString("data.name");

        /*System.out.println("Subnet created successfully with id " + subnetID);
        System.out.println("Subnet status is " + subnetStatus);
        System.out.println("Subnet name is " + subnetName);
    }*/


    @Test(priority = 1)
    public void createServer(){
        //waitForSubnetToBeActive(subnetName,networkID);
       // String dayTest;
        int creationTime;
        serverName = ServerController.generateServerRandomName();
        Response createResponse = ServerService.createServer(serverName);
        Assert.assertEquals(createResponse.getStatusCode(), 202,
                "Expected status code 202, but received: " + createResponse.getStatusCode());

        //wait for server to be active
        waitForServerToBeActive(serverId);

        //test create server time
        creationTime = ServerController.getServerCreationTimeSeconds(serverName);
        Assert.assertNotEquals(creationTime, 3000);

        serverId = createResponse.jsonPath().getString("data.uuid");

        //dayTest = createResponse.jsonPath().getString("data.createdAt"); //check this again
        //Date expectedDate = Converter.convertToDate(dayTest);

        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        //verify created information
        Assert.assertEquals(serverData.getUuid(), serverId, "Server ID should match");
        Assert.assertEquals(serverData.getName(), serverName, "Server name should match");
        //Assert.assertEquals(serverData.getCreatedAt().toString(), expectedDate.toString(), "Created day should be match" );
        Assert.assertEquals(serverData.getStatus(), "ACTIVE", "Server status should be ACTIVE");
    }

    @Test(priority = 2, dependsOnMethods = "createServer")
    public void RenameTest(){
        String serverNameTest = "thinhna02";
        Response response = ServerService.RenameServer(serverId);
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200, but received: " + response.getStatusCode());

        serverName = response.jsonPath().getString("data.name");

        //make sure the new name is created correctly
        Assert.assertEquals(serverName,serverNameTest,"The new server name should be match");
    }

    @Test(priority = 3)
    public void ResizeServer() {
        String dataUrl;
        Response response = ServerService.ResizeServer(serverId);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200, but received: " + response.getStatusCode());

        dataUrl = response.jsonPath().getString("redirectUrl");
        Assert.assertNotNull(dataUrl, "Data url can not be null");

        //simulate the web driver action of user
        SeleniumController.redirectToPayment(dataUrl);

        //ServerController.waitForServerToBeResized(serverId,vServerData.flavorIDTestUp);
        waitForServerToBeActive(serverId);

        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        //make sure the server is resize up correctly
        Assert.assertEquals(serverData.getFlavor().getFlavorId(), vServerData.flavorIDTestUp, "flavor id up should match");
    }

   @Test(priority = 4, dependsOnMethods = "ResizeServer")
    public void ResizeServerDown() {
        String dataUrl;
        Response response = ServerService.ResizeServerDown(serverId);
        dataUrl = response.jsonPath().getString("redirectUrl");

        //simulate the web driver action of user
        SeleniumController.redirectToPayment(dataUrl);
        waitForServerToBeActive(serverId);

        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        //make sure the server is resize down correctly
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200, but received: " + response.getStatusCode());
        Assert.assertEquals(serverData.getFlavor().getFlavorId(), vServerData.flavorIDTestDown, "flavor id down should match");
    }



    @Test(priority = 5)
    public void CreateImageTest(){
        String imageStatus, projectImageID, ImageName;
        Response response = ServerService.CreateImage(serverId);
        Assert.assertEquals(response.getStatusCode(), 202,
                "Expected status code 202, but received: " + response.getStatusCode());

        imageStatus = response.jsonPath().getString("data.status");
        projectImageID = response.jsonPath().getString("data.projectId");
        ImageName = response.jsonPath().getString("data.name");
        System.out.println("Image status is now " + imageStatus);

        //make sure the image is creating - add more assertion as needed
        Assert.assertEquals(imageStatus, "CREATING", "Image status should match");
        Assert.assertEquals(projectImageID, projectId, "projectId should match");
        Assert.assertEquals(ImageName,"ImageTest","Image name should match");
    }

   @Test(priority = 6)
   public void updateSecurityAndVerify() {
       String status;
       Response response = ServerService.UpdateSecurity(serverId);

       Assert.assertEquals(response.getStatusCode(), 202,
               "Expected status code 202, but received: " + response.getStatusCode());
       status = response.jsonPath().getString("data.status");
       Assert.assertEquals(status, "CHANGING-SECURITY-GROUP", "Status is supposed to be matching");

       waitForServerToBeActive(serverId);

       // Re-fetch server data to get the updated secGroups
       GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
       Data serverData = generalInfo.getData();

       // Extract the first element from secGroups
       String secGroupId = serverData.getSecGroups().get(0).getUuid();
       String secGroupName = serverData.getSecGroups().get(0).getName();

       System.out.println(secGroupId + " " + secGroupName);

       // Make sure the new security group is added
       Assert.assertEquals(secGroupId, vServerData.secGroupID, "Security group ID should match");
       Assert.assertEquals(secGroupName, vServerData.secGroupName, "Security group name should match");
   }


    //missing delete network and subnet after being created to test server
    //test delete server + network + subnet
    @AfterTest
    public void deleteServer() {
        String serverStatus = ServerController.getServerStatus(serverName);
        if ("ACTIVE".equals(serverStatus)) {
            ServerService.deleteServer(serverName);
        }
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

    private void waitForNetworkToBeActive(String networkID){
            int maxRetries = 10; // maximum number of retries
            int retryInterval = 30; // interval between retries in seconds

            for (int i = 0; i < maxRetries; i++) {
                String networkStatus = ServerController.getNetworkStatus(networkName);
                if ("ACTIVE".equals(networkStatus)) {
                    return;
                }

                try {
                    TimeUnit.SECONDS.sleep(retryInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Assert.fail("Network did not reach 'ACTIVE' status within the expected time");
    }

    private void waitForSubnetToBeActive(String subnetName,String networkID){
        int maxRetries = 10; // maximum number of retries
        int retryInterval = 30; // interval between retries in seconds

        for (int i = 0; i < maxRetries; i++) {
            String subnetStatus = ServerController.getSubnetStatus(subnetName, networkID);
            if ("ACTIVE".equals(subnetStatus)) {
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.fail("Network did not reach 'ACTIVE' status within the expected time");
    }
}
