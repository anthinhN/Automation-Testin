package vserver.han01.server;

import dto.vServerr.han01.response.server.ServerListData.ListData;
import dto.vServerr.response.server.GetServerListData;
import helper.ExpectedServerValues;
import helper.ServerTestHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.vserver.han01.ServerService;

public class TestViewListOfServer {

    @Test(priority = 1)
    public void printServerUUIDs() {
        // Retrieve the list of server data
        GetServerListData serverListData = ServerService.getListServerResponse();

        // Ensure the server list data is not null
        Assert.assertNotNull(serverListData, "Server list data should not be null");

        // Ensure the listData field is not null
        Assert.assertNotNull(serverListData.getListData(), "ListData should not be null");

        //store all information in test helper
        for (ListData server : serverListData.getListData()) {
            ServerTestHelper.addServerDetails(
                    server.getUuid(),
                    new ExpectedServerValues(
                            server.getName(),
                            server.getStatus(),
                            server.getFlavor().getName(),
                            server.getImage().getImageType(),
                            server.getImage().getImageVersion()
                    )
            );
        }

        // Print actual UUIDs for debugging
        System.out.println("Actual UUIDs: " + ServerTestHelper.getAllUUIDs());

    }

    @Test(priority = 2)
    public void verifyListOfServer(){
        GetServerListData serverListData = ServerService.getListServerResponse();

        //ensure list of data is not null
        Assert.assertNotNull(serverListData, "List of Data is not supposed to be null");

        // Ensure the listData field is not null
        Assert.assertNotNull(serverListData.getListData(), "ListData should not be null");

        // Print expected UUIDs for debugging
        System.out.println("Expected UUIDs: " + ServerTestHelper.getAllUUIDs());

        boolean allServersVerified = true;

        //Display correct info - by iterate through the array list
        for (ListData server : serverListData.getListData()) {
            // Ensure the server fields are not null or empty
            Assert.assertNotNull(server.getUuid(), "Server ID should not be null");
            Assert.assertNotNull(server.getName(), "Server name should not be null");
            Assert.assertNotNull(server.getStatus(), "Server status should not be null");
            Assert.assertNotNull(server.getFlavor(), "Server instance type should not be null");
            Assert.assertNotNull(server.getImage().getImageType(), "Server Instance Image type should not be null");
            Assert.assertNotNull(server.getImage().getImageVersion(),"Server Instance Image version can not be null");

            // Print the server information for debugging
            System.out.println("Server ID: " + server.getUuid());
            System.out.println("Server Name: " + server.getName());
            System.out.println("Server Status: " + server.getStatus());
            System.out.println("Server Instance Type: " + server.getFlavor().getName());
            System.out.println("Server Image Type: " + server.getImage().getImageType());
            System.out.println("System Image Version: " + server.getImage().getImageVersion());

            // Retrieve expected values for the current server
            ExpectedServerValues expectedValues = ServerTestHelper.getServerDetails(server.getUuid());

            // Ensure the expected values are not null
            if (expectedValues == null) {
                System.out.println("No expected values found for server ID: " + server.getUuid());
                allServersVerified = false;
                continue;
            }

            // Print the expected values for debugging
            System.out.println("Expected Name: " + expectedValues.getName());
            System.out.println("Expected Status: " + expectedValues.getStatus());
            System.out.println("Expected Instance Type: " + expectedValues.getInstanceType());
            System.out.println("Expected OS: " + expectedValues.getImageType());
            System.out.println("Expected Version: " + expectedValues.getImageVersion());

            // Verify the server fields with expected values
            try {
                Assert.assertEquals(server.getName(), expectedValues.getName(), "Server name should match");
                Assert.assertEquals(server.getStatus(), expectedValues.getStatus(), "Server status should match");
                Assert.assertEquals(server.getFlavor().getName(), expectedValues.getInstanceType(), "Server instance type should match");
                Assert.assertEquals(server.getImage().getImageType(), expectedValues.getImageType(), "Server OS should match");
                Assert.assertEquals(server.getImage().getImageVersion(), expectedValues.getImageVersion(), "Server version should match");
            } catch (AssertionError e) {
                System.out.println("Assertion failed for server ID: " + server.getUuid());
                allServersVerified = false;
            }
        }

        // Ensure all servers have been verified correctly
        Assert.assertTrue(allServersVerified, "All servers should be verified correctly");
        }
    }

