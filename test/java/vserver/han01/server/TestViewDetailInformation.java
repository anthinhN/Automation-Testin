package vserver.han01.server;

import dto.vServerr.response.actions.Data;
import dto.vServerr.response.actions.GetResponseActionsOfServerData;
import dto.vServerr.han01.response.secgroup.BoundResponse;
import dto.vServerr.response.server.NetworkInterface.GetResponseNetworkInterfaceOfServerData;
import dto.vServerr.response.server.ServerListData.Interface;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.vserver.han01.ServerService;
import data.vserver.vServerData;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**Server information has changed, modify information accordingly**/
public class TestViewDetailInformation {
    private String serverId = "ins-e7c7ec6a-c7ae-4b3d-9760-3dc5e472d0c4"; //this id only work for test 1

    //test volume information in server
    @Test(priority = 1) //if have time change server id so this align with the rest
    public void verifyVolumeInformationInServer(){
        GetResponseVolumeByServerData volumeResponse = ServerService.getVolumeResponseInServer(serverId);

        // Ensure the response is successful
        Assert.assertTrue(volumeResponse.isSuccess(), "Expected success to be true, but it was false.");

        // Validate the volume information
        List<Volume> volumes = volumeResponse.getVolumes();
        Assert.assertNotNull(volumes, "Volumes list should not be null.");
        Assert.assertFalse(volumes.isEmpty(), "Volumes list should not be empty.");

        //If have time modify this - so that they will check actual data by taking in actual response
        for (Volume volume : volumes) {
            Assert.assertEquals(volume.getUuid(),vServerData.VolumeID, "Volume ID should be match.");
            Assert.assertEquals(volume.getName(),vServerData.VolumeName ,"Volume name should be match.");
            Assert.assertEquals(volume.getSize(), vServerData.diskSize, "Volume size should be match");
            Assert.assertEquals(volume.getVolumeTypeZoneName(), vServerData.VolumeTypeZoneName ,"Volume type should be match");
            Assert.assertEquals(volume.getIops(), vServerData.IOPS," IOPS should be match");
            Assert.assertEquals(volume.getEncryptionType(), vServerData.encryptionType,"Encryption type should be match");
        }
    }

    @Test(priority = 2)
    public void verifyHistoryLog(){
        String serverId = "ins-47b4bf4f-f135-4973-98d8-a639327d5c62";
        ZonedDateTime expectedDate = vServerData.startTime;

        GetResponseActionsOfServerData getResponseActionsOfServerData = ServerService.getHistoryActionInServer(serverId);

        //ensure the list is not null
        Assert.assertNotNull(getResponseActionsOfServerData,"the list is not suppose to be null");

        //ensure the list field is not null
        Assert.assertNotNull(getResponseActionsOfServerData.getData(),"The data in the list is not suppose to be null");

        ArrayList<Data> data = getResponseActionsOfServerData.getData();

        for(Data data1 : data){
            // Convert actual date to ZonedDateTime in the same time zone as expectedDate
            ZonedDateTime actualDate = ZonedDateTime.ofInstant(data1.getStartTime().toInstant(), expectedDate.getZone());

            // Format both dates for comparison
            DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            String expectedDateStr = expectedDate.format(formatter);
            String actualDateStr = actualDate.format(formatter);

            //verify information so that it is display correctly
            Assert.assertEquals(data1.getAction(), vServerData.action, "Action should match");
            Assert.assertEquals(actualDateStr,expectedDateStr, "Start time should match");
            Assert.assertEquals(data1.getUserAction(), vServerData.userAction, "User action should match");
        }
    }

    /**this code only test the first element in the security group in server
     * change code if needed**/
    @Test(priority = 3)
    public void verifySecGroup(){
        String serverId = "ins-47b4bf4f-f135-4973-98d8-a639327d5c62";
        SecGroupResponse secGroupResponse = ServerService.getSecGroupResponse(serverId);

        //ensure the list is not null
        Assert.assertNotNull(secGroupResponse, "Security Group is not suppose to be null");

        //ensure the list field is not null for both inbound and outbound rules
        Assert.assertNotNull(secGroupResponse.getData().getInbounds(), "Inbound rule is not suppose to be null");
        Assert.assertNotNull(secGroupResponse.getData().getOutbounds(), "Outbound rule is not suppose to be null");

        ArrayList<BoundResponse> inbounds = secGroupResponse.getData().getInbounds();
        ArrayList<BoundResponse> outbounds = secGroupResponse.getData().getOutbounds();

        //only check the first data of the array list - if can, try and modify so that it will check everything
        BoundResponse firstInboundResponseData = inbounds.get(0);
        Assert.assertEquals(firstInboundResponseData.getId(), vServerData.SecGroupInboundID);
        Assert.assertEquals(firstInboundResponseData.getSecGroupName(),vServerData.SecGroupNameInbound);
        Assert.assertEquals(firstInboundResponseData.getDescription(), vServerData.SecGroupDescriptionInbound);
        Assert.assertEquals(firstInboundResponseData.getProtocol(), vServerData.SecGroupProtocolInbound);
        Assert.assertEquals(firstInboundResponseData.getRemoteIpPrefix(), vServerData.SecGroupRemoteIpPrefixInbound);
        Assert.assertEquals(firstInboundResponseData.getEtherType(), vServerData.SecGroupEtherTypeInbound);
        Assert.assertEquals(firstInboundResponseData.getPortRangeMin(), vServerData.SecGroupPortRangeMinInbound);
        Assert.assertEquals(firstInboundResponseData.getPortRangeMax(), vServerData.SecGroupPortRageMaxInbound);

        BoundResponse firstOutboundResponseData = outbounds.get(0);
        Assert.assertEquals(firstOutboundResponseData.getId(), vServerData.SecGroupOutboundID);
        Assert.assertEquals(firstOutboundResponseData.getSecGroupName(),vServerData.SecGroupNameOutbound);
        Assert.assertEquals(firstOutboundResponseData.getDescription(), vServerData.SecGroupDescriptionOutbound);
        Assert.assertEquals(firstOutboundResponseData.getProtocol(), vServerData.SecGroupProtocolOutbound);
        Assert.assertEquals(firstOutboundResponseData.getRemoteIpPrefix(), vServerData.SecGroupRemoteIpPrefixOutbound);
        Assert.assertEquals(firstOutboundResponseData.getEtherType(), vServerData.SecGroupEtherTypeOutbound);
        Assert.assertEquals(firstOutboundResponseData.getPortRangeMin(), vServerData.SecGroupPortRangeMinOutbound);
        Assert.assertEquals(firstOutboundResponseData.getPortRangeMax(), vServerData.SecGroupPortRageMaxOutbound);
    }

    @Test(priority =4)
    public void verifyNetworkInterface(){
        String serverId = "ins-e7c7ec6a-c7ae-4b3d-9760-3dc5e472d0c4";
        GetResponseNetworkInterfaceOfServerData getResponseNetworkInterfaceOfServerData = ServerService.getResponseNetworkInterfaceOfServerData(serverId);

        // ensure the list is not null
        Assert.assertNotNull(getResponseNetworkInterfaceOfServerData, "The list is not suppose to be null");

        //ensure the data in the list is not null
        Assert.assertNotNull(getResponseNetworkInterfaceOfServerData.getData().getInternalInterfaces(),
                "The data in Internal Interface is not suppose to be null");

        List<Interface> internalInterface = getResponseNetworkInterfaceOfServerData.getData().getInternalInterfaces();

        // make sure the information is display correctly for Network Interface
        Interface firstInterfaceResponseData = internalInterface.get(0);
        Assert.assertEquals(firstInterfaceResponseData.getUuid(), vServerData.InternalNetworkID);
        Assert.assertEquals(firstInterfaceResponseData.getNetworkUuid(), vServerData.InternalNetworkVPCID);
        Assert.assertEquals(firstInterfaceResponseData.getSubnetUuid(), vServerData.InternalNetworkSubnetID);
        Assert.assertEquals(firstInterfaceResponseData.getFixedIp(), vServerData.InternalNetworkFixedIP);
        Assert.assertEquals(firstInterfaceResponseData.getFloatingIp(), vServerData.InternalNetworkFloatingIP);

        //assert external information here - the list is null right now so can't do compare
    }




}


