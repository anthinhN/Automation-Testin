package vserver.han01.server;

import dto.vServerr.response.actions.Console.ConsoleResponseAction;
import dto.vServerr.response.server.GetGeneralInformationServerData;
import dto.vServerr.response.server.ServerResponseData.Data;
//import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.vserver.han01.ServerService;
import data.vserver.vServerData;
import controller.vserver.han01.*;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class TestActionInServer {
    String serverId = "ins-e7c7ec6a-c7ae-4b3d-9760-3dc5e472d0c4";
    String serverName = "thinhna-auto-no-delete";

    // If there is time modify test 1 so that after it created the URL + token,
    // it then will check if the new created URL+ token is the same as the response one
    @Test(priority = 1)
    public void ConsoleRequest(){
        ConsoleResponseAction consoleResponseAction = ServerService.consoleResponseAction(serverId);

        //ensure the response is not null
        assertNotNull(consoleResponseAction, "The response is not suppose to be null");

        //ensure the data in the response is not null
        assertNotNull(consoleResponseAction.getData(), "Data in the response is not suppose to be null");

        String actualUrl = consoleResponseAction.getData();
        String expectedUrl = vServerData.dataURl;

        String actualBaseUrl = actualUrl.split("\\?")[0];
        String expectedBaseUrl = expectedUrl.split("\\?")[0];

        // Assert that the base URLs are the same
        Assert.assertEquals(actualBaseUrl, expectedBaseUrl, "Base URL should match");

        // Print the tokens for debugging
        String actualToken = actualUrl.contains("?") ? actualUrl.split("\\?")[1] : "";
        String expectedToken = expectedUrl.contains("?") ? expectedUrl.split("\\?")[1] : "";

        System.out.println("Actual Token: " + actualToken);
        System.out.println("Expected Token: " + expectedToken);
    }

    @Test(priority = 2)
    public void TestChangeOfStatusRequest(){
        Response createResponse = ServerService.ChangeToRebootStatusOfServer(serverId);
        Assert.assertEquals(createResponse.getStatusCode(), 202,
                "Expected status code 202, but received:" + createResponse.getStatusCode());
        //debug
        //System.out.println("Status " + createResponse.getStatusCode());

        waitForServerToBeARebooting(serverId);

        // Get the general information of the server
        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        // Ensure serverData is not null
        Assert.assertNotNull(serverData, "Server data should not be null");

        System.out.println("Server status is now " + serverData.getStatus());

        //Assert method
        Assert.assertEquals(serverData.getStatus(), vServerData.reBootStatus);
    }

    @Test(priority = 3)
    public void TestChangeOfStatusRequestShutDown(){
        String serverStatus = ServerController.getServerStatus(serverName);
        if("ACTIVE".equals(serverStatus)){
        Response createResponse = ServerService.ChangeToShutdownStatusOfServer(serverId);
        Assert.assertEquals(createResponse.getStatusCode(), 202,
                "Expected status code 202, but received:" + createResponse.getStatusCode());
        waitForServerToBeShuttingDown(serverId);

        // Get the general information of the server
        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        // Ensure serverData is not null
        Assert.assertNotNull(serverData, "Server data should not be null");

        System.out.println("Server status is now " + serverData.getStatus());

        //Assert method
        Assert.assertEquals(serverData.getStatus(), vServerData.StopStatus);}
        else{
            System.out.println("This action can not be done right now");
        }
    }

    @Test(priority = 4)
    public void TestChangeOfStatusRequestStart(){
        String serverStatus = ServerController.getServerStatus(serverName);
        if("STOPPED".equals(serverStatus)){
        Response createResponse = ServerService.ChangeToStartStatusOfServer(serverId);
        Assert.assertEquals(createResponse.getStatusCode(), 202,
                "Expected status code 202, but received:" + createResponse.getStatusCode());
        //debug
        //System.out.println("Status " + createResponse.getStatusCode());

        waitForServerToBeStarting(serverId);

        // Get the general information of the server
        GetGeneralInformationServerData generalInfo = ServerService.getGeneralInformationServerData(serverId);
        Data serverData = generalInfo.getData();

        // Ensure serverData is not null
        Assert.assertNotNull(serverData, "Server data should not be null");

        System.out.println("Server status is now " + serverData.getStatus());

        //Assert method
        Assert.assertEquals(serverData.getStatus(), vServerData.StartStatus);}
        else{
            System.out.println("This action can not be done right now");
        }
    }

    private void waitForServerToBeARebooting(String serverId) {
        int maxRetries = 10; // maximum number of retries
        int retryInterval = 30; // interval between retries in seconds

        for (int i = 0; i < maxRetries; i++) {
            String serverStatus = ServerController.getServerStatus(serverName);
            if ("REBOOTING".equals(serverStatus)) {
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

    private void waitForServerToBeStarting(String serverId) {
        int maxRetries = 10; // maximum number of retries
        int retryInterval = 30; // interval between retries in seconds

        for (int i = 0; i < maxRetries; i++) {
                String serverStatus = ServerController.getServerStatus(serverName);
            if ("STARTING".equals(serverStatus)) {
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.fail("Server did not reach 'STARTING' status within the expected time");
    }
    private void waitForServerToBeShuttingDown(String serverId) {
        int maxRetries = 10; // maximum number of retries
        int retryInterval = 30; // interval between retries in seconds

        for (int i = 0; i < maxRetries; i++) {
            String serverStatus = ServerController.getServerStatus(serverName);
            if ("TURNING-OFF".equals(serverStatus)) {
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.fail("Server did not reach 'TURNING-OFF' status within the expected time");
    }

}
