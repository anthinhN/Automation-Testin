package service.vserver.han;

import com.google.gson.Gson;
import converter.Converter;
import dto.vServerr.request.actions.CreateImage.CreateImageServerAction;
import dto.vServerr.request.actions.RenameFile.CreateRenameServerRequest;
import dto.vServerr.request.actions.Resize.ResizeRequestInServer;
import dto.vServerr.request.actions.Resize.ResourceInfo;
import dto.vServerr.request.actions.UpdateSecurity.UpdateSecurityInServerAction;
import dto.vServerr.request.createServer.createServer;
import dto.vServerr.request.network.CreateNetwork;
import dto.vServerr.request.serverGroup.CreateServerGroup;
import dto.vServerr.response.ServerGroup.ServerGroupData;
import dto.vServerr.response.actions.Console.ConsoleResponseAction;
import dto.vServerr.response.actions.GetResponseActionsOfServerData;
import dto.vServerr.han01.response.secgroup.SecGroupResponse;
import dto.vServerr.response.server.GetGeneralInformationServerData;
import dto.vServerr.response.server.GetServerListData;
import dto.vServerr.response.server.NetworkInterface.GetResponseNetworkInterfaceOfServerData;
import dto.vServerr.response.ServerGroup.ServerGroupListDataResponse;
import dto.vServerr.request.subnet.CreateSubnet;
import dto.vServerr.han01.response.subnet.GetSubnetListData;
import dto.vServerr.han01.response.volume.GetResponseVolumeByServerData;
import dto.vServerr.response.server.ServerResponseData.Data;
import dto.vServerr.response.ssh.SSHCreateResponse;
import dto.vServerr.response.vpc.GetVPCListData;
import io.restassured.response.Response;
import service.restassured.RestService;
import service.viam.AuthenticationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.vserver.han01.ServerController;

public class ServerService extends RestService {
    private static final String baseUri = ""; //
    private static final String projectId = ""; //input new project id
    static RestService restService = new ServerService();

    @Override
    public Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", AuthenticationService.getBearerToken());
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * Server related
     **/
    //Create server with most OS except for Windows - write separate one
    public static Response createServer(String serverName) {
        String basePath = String.format("", projectId); // Updated format for basePath
        dto.vServerr.request.createServer.createServer createServer = new createServer();
        createServer.setName(serverName);
        createServer.setCreatedFrom("NEW");
        createServer.setAttachFloating(true);
        createServer.setBackupInstancePointId(null);
        createServer.setPoc(false);

        ArrayList<String> configVolumeRestores = new ArrayList<>();

        createServer.setConfigVolumeRestores(configVolumeRestores);

        ArrayList<String> tags = new ArrayList<>();
        createServer.setTags(tags);

        createServer.setDataDiskName(null);
        createServer.setEnableBackup(false);
        createServer.setBuyMorePoc(false);
        createServer.setDataDiskSize(null);
        createServer.setBackupInstancePointId(null);
        createServer.setDataDiskEncryptionType(null);
        createServer.setEnableAutoRenew(true);
        createServer.setEncryptionVolume(true);
        createServer.setDataDiskTypeId(null);
        createServer.setExpirePassword(false);
        createServer.setFlavorId(""); //fill
        createServer.setHostGroupId(null);
        createServer.setImageId(""); //fill

        createServer.setNetworkId(""); //
        createServer.setSubnetId("");

        //createServer.setNetworkId(networkId);
        //createServer.setSubnetId(subnetId);

        createServer.setOsLicence(false);

        createServer.setRootDiskEncryptionType(null);
        createServer.setRootDiskTypeId(""); //fill

        ArrayList<String> secGroup = new ArrayList<>();
        secGroup.add(""); //fill

        createServer.setSecurityGroup(secGroup);
        createServer.setServerGroupId(null);
        createServer.setRootDiskSize(20);
        createServer.setSnapshotInstancePointId(null);
        createServer.setSshKeyId(null); //fill
        createServer.setUserName(null); //fill -
        createServer.setUserPassword(null); //fill -
        createServer.setUserData(null);
        createServer.setUserDataBase64Encoded(false);
        
        Response response = restService.sendPostRequest(baseUri, basePath, createServer);
        return response;
    }

    public static GetServerListData getListServerResponse() {
        String basePath = String.format("", projectId);
        Response response = restService.sendGetRequest(baseUri, basePath);
        return Converter.convertJSONToDTO(response.asString(), GetServerListData.class) ;
    }

    public static GetResponseVolumeByServerData getVolumeResponseInServer(String serverId){
        String basePath = String.format("" ,projectId,serverId);
        Response response = restService.sendGetRequest(baseUri, basePath);
        return Converter.convertJSONToDTO(response.asString(), GetResponseVolumeByServerData.class);
    }

    public static GetResponseActionsOfServerData getHistoryActionInServer(String serverID){
        String basePath = String.format("", projectId, serverID);
        Response response = restService.sendGetRequest(baseUri,basePath);


        return Converter.convertJSONToDTO(response.asString(), GetResponseActionsOfServerData.class);
    }

    public static SecGroupResponse getSecGroupResponse(String serverID){
        String basePath = String.format("", projectId,serverID);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), SecGroupResponse.class);
    }

    public static GetGeneralInformationServerData getGeneralInformationServerData(String serverID){
        String basePath = String.format("", projectId, serverID);
        Response response = restService.sendGetRequest(baseUri, basePath);

        // Print the response body for debugging
        //System.out.println("Response Body: " + response.getBody().asString());

        return Converter.convertJSONToDTO(response.asString(), GetGeneralInformationServerData.class);
    }

    public static Data getServerDetails(String serverID){
        String basePath = String.format("", projectId,serverID);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(),Data.class);
    }
    public static void deleteServer(String serverName) {
        String serverId = ServerController.getServerId(serverName);
        String body = "{\"deleteAllVolume\": false}";
        String basePath = String.format("", projectId, serverId); // Updated format for basePath
        restService.sendDeleteRequest(baseUri, basePath, body);
    }

    /**Network Interface related**/
    public static GetResponseNetworkInterfaceOfServerData getResponseNetworkInterfaceOfServerData(String serverId){
        String basePath = String.format("", projectId, serverId);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), GetResponseNetworkInterfaceOfServerData.class);
    }

    /**
     * Group Server Related
     **/

    //Get service
    public  static ServerGroupListDataResponse ServerGroupListDataResponse() {
        String basePath = String.format("", projectId);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), ServerGroupListDataResponse.class);
    }

    //Get server group response data
    public static ServerGroupData serverGroupData(String serverGroupID) {
        String basePath = String.format("", projectId,serverGroupID);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), ServerGroupData.class);
    }

    //create server group
    public static Response createServerGroup(String serverGroupName){
        String basePath = String.format("", projectId);
        dto.vServerr.request.serverGroup.CreateServerGroup createServerGroup = new CreateServerGroup();
        createServerGroup.setName(serverGroupName);
        createServerGroup.setDescription("rrr");
        createServerGroup.setPolicyId("");
        return restService.sendPostRequest(baseUri,basePath, createServerGroup);
    }

    //delete service group request - check again
    public static void deleteServerGroup(String serverGroupID){
        String basePath = String.format("", projectId, serverGroupID);
        restService.sendDeleteRequest(baseUri,basePath);
    }



    /**
     * Subnet related
     **/

    //create subnet
    public static Response createSubnet(String networkId){
        String basePath = String.format(", projectId,networkId);
        CreateSubnet createSubnet = new CreateSubnet();
        createSubnet.setName("testSubnet1345");
        createSubnet.setCidr("");
        return restService.sendPostRequest(baseUri, basePath, createSubnet);
    }
    //get service
    public static GetSubnetListData getSubnetListData(String networkId) {
        String basePath = String.format("", projectId, networkId);
        Response response = restService.sendGetRequest(baseUri,basePath);

        System.out.println("The response body is " + response.getBody().asString());
        return Converter.convertJSONToDTO(response.asString(), GetSubnetListData.class);
    }

    //delete request - check again
    public static void deleteSubnet(String networkId, String subnetID) {
        String basePath = String.format("", projectId, networkId, subnetID);
        restService.sendDeleteRequest(baseUri, basePath);
    }


    /** SSH Key related **/
    public static Response SShCreateRequest(String sshName){
        String basePath = String.format("", projectId); //filled with ssh base path
        SSHCreateRequest sshCreateRequest = new SSHCreateRequest();
        sshCreateRequest.setName(sshName);
        return restService.sendPostRequest(baseUri, basePath, sshCreateRequest);
    }

    public static Response sshImportRequest(String sshName){
        String basePath = String.format("/v1/projects/%s/", projectId);
        dto.vServerr.request.ssh.SSHImportRequest sshImportRequest = new dto.vServerr.request.ssh.SSHImportRequest();
        sshImportRequest.setName(sshName);
        return restService.sendPostRequest(baseUri, basePath, sshImportRequest);
    }

    //get service
    public static dto.vServerr.response.ssh.SSHList getSSHList(){
        String basePath = String.format("", projectId);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), dto.vServerr.response.ssh.SSHList.class);
    }

   public static dto.vServerr.response.ssh.SSHCreateResponse sshCreateResponse(){
       String basePath = String.format("", projectId);
       Response response = restService.sendGetRequest(baseUri,basePath);
       return Converter.convertJSONToDTO(response.asString(), SSHCreateResponse.class);
   }

   public static dto.vServerr.response.ssh.SSHInvalidResponse sshInvalidResponse(){
       String basePath = String.format("", projectId);
       Response response = restService.sendGetRequest(baseUri,basePath);
       return Converter.convertJSONToDTO(response.asString(), dto.vServerr.response.ssh.SSHInvalidResponse.class);
   }

    public static dto.vServerr.response.ssh.SSHData sshData(){
        String basePath = String.format("", projectId);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(), dto.vServerr.response.ssh.SSHData.class);
    }

    /**
     * network
     **/
    public static Response createNetwork(String networkName){
        String basePath = String.format("v", projectId);
        CreateNetwork createNetwork = new CreateNetwork();
        createNetwork.setName(networkName);
        createNetwork.setCidr("10.3.0.0/16");

        ArrayList<String> tags = new ArrayList<>();
        createNetwork.setTags(tags);

        System.out.println("Request Body: " + new Gson().toJson(createNetwork));
        Response response = restService.sendPostRequest(baseUri,basePath,createNetwork);
        System.out.println("Response body: " + response.getBody().asString());
        return response;
    }

    public static void DeleteNetwork(String networkID){
        String basePath = String.format("",projectId, networkID);
        restService.sendDeleteRequest(baseUri,basePath);
    }

    //get list of server
    public static GetVPCListData getVPCListData(){
        String basePath = String.format("", projectId);
        Response response = restService.sendGetRequest(baseUri,basePath);
        return Converter.convertJSONToDTO(response.asString(),GetVPCListData.class);
    }

    /**Server Action related**/
    public static ConsoleResponseAction consoleResponseAction(String serverId){
        String basepath = String.format("", projectId,serverId);
        Response response = restService.sendGetRequest(baseUri,basepath);
        return Converter.convertJSONToDTO(response.asString(), ConsoleResponseAction.class);
    }
    /**Server status related**/
    public static Response ChangeToStartStatusOfServer(String serverId){
        String basePath = String.format("", projectId,serverId);
        Response response = restService.sendPutRequest(baseUri, basePath);
        return response;
    }
    public static Response ChangeToShutdownStatusOfServer(String serverId){
        String basePath = String.format("", projectId,serverId);
        Response response = restService.sendPutRequest(baseUri, basePath);
        return response;
    }

    public static Response ChangeToRebootStatusOfServer(String serverId){
        String basePath = String.format("", projectId,serverId);
        Response response = restService.sendPutRequest(baseUri, basePath);
        return response;
    }
    /**Resize Server**/
    public static Response ResizeServer(String serverId){
        String basePath = String.format("", projectId);
        ResizeRequestInServer resizeRequestInServer = new ResizeRequestInServer();

        resizeRequestInServer.setRespurceType("server");
        resizeRequestInServer.setAction("resize");

        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setServerId(serverId);
        resourceInfo.setFlavorId("");
        resourceInfo.setHostGroupId(null);
        resizeRequestInServer.setResourceInfo(resourceInfo);

        Response response = restService.sendPostRequest(baseUri,basePath,resizeRequestInServer);

        return response;
    }

    public static Response ResizeServerDown(String serverId){
        String basePath = String.format(", projectId);
        ResizeRequestInServer resizeRequestInServer = new ResizeRequestInServer();
        resizeRequestInServer.setRespurceType("server");
        resizeRequestInServer.setAction("resize");

        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setServerId(serverId);
        resourceInfo.setFlavorId("");
        resourceInfo.setHostGroupId(null);
        resizeRequestInServer.setResourceInfo(resourceInfo);

        Response response = restService.sendPostRequest(baseUri,basePath,resizeRequestInServer);

        // Print the response for debugging
        System.out.println("Create Server Response: " + response.getBody().asString());

        return response;
    }
    /**Rename Server**/
    public static Response RenameServer(String serverId){
        String basePath = String.format("", projectId, serverId);
        CreateRenameServerRequest createRenameServerRequest = new CreateRenameServerRequest();
        createRenameServerRequest.setNewName("thinhna02");
        Response response = restService.sendPutRequest(baseUri,basePath,createRenameServerRequest);
        return response;
    }
     /**Image Server Service Related**/
     public static Response CreateImage(String serverId){
         String basePath = String.format("", projectId, serverId);
         CreateImageServerAction createImageServerAction = new CreateImageServerAction();
         createImageServerAction.setName("ImageTest");
         ArrayList<String> tags = new ArrayList<>();
         createImageServerAction.setTags(tags);

         System.out.println("Request Body: " + new Gson().toJson(createImageServerAction)); // Print the request body
         Response response = restService.sendPostRequest(baseUri, basePath, createImageServerAction);

         // Print the response for debugging
         System.out.println("Create Server Response: " + response.getBody().asString());

         return response;
     }


     /**Update security Group**/
     public static Response UpdateSecurity(String serverId){
         String basePath = String.format("", projectId, serverId);
         UpdateSecurityInServerAction updateSecurityInServerAction = new UpdateSecurityInServerAction();

         ArrayList<String> securityGroup = new ArrayList<>();
         securityGroup.add("");
         updateSecurityInServerAction.setSecurityGroup(securityGroup);

         //System.out.println("Request Body: " + new Gson().toJson(updateSecurityInServerAction)); // Print the request body
         Response response = restService.sendPutRequest(baseUri, basePath, updateSecurityInServerAction);

         // Print the response for debugging
         System.out.println("Create Server Response: " + response.getBody().asString());

         return response;
     }
    /**
     * Floating IP
     **/

    /**
     * Quota Limit
     **/
}



