package vserver.hcm03;

import controller.filecontroller.ExcelController;
import controller.vserver.hcm03.vLBController;
import data.vLBData;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import service.vmonitor.MetricService;
import service.vserver.hcm03.vLBService;

public class TestLB7CreationTime {
    private String loadBalancerName;
    private String poolName;
    private String listenerName;
    private int loadBalancerCreationTimeSeconds;
    private int poolCreationTime;
    private int listenerCreationTime;

    @BeforeTest
    public void setupTest() {
        loadBalancerName = vLBController.generateLoadBalancerRandomName();
        poolName = vLBController.generatePoolRandomName();
        listenerName = vLBController.generateListenerRandomName();
    }

    @Test(priority = 1)
    public void createLoadBalancer() {
        vLBService.createLoadBalancerLayer7(loadBalancerName);
    }

    @Test(priority = 2)
    public void getLoadBalancerCreationTimeAndSendMetric() {
        // Get load balancer creation time
        loadBalancerCreationTimeSeconds = vLBController.getLoadBalancerCreationTime(loadBalancerName);
        // Send metric of load balancer to vMonitor
        MetricService.sendLoadBalancerMetric(loadBalancerName, loadBalancerCreationTimeSeconds);

        // If load balancer creation time = 3600 then failed test script
        Assert.assertNotEquals(loadBalancerCreationTimeSeconds, vLBData.WAIT_SECONDS);
    }

    @Test(priority = 3)
    public void createPool() {
        vLBService.createPoolWithAttachedMemberForLB7(poolName, loadBalancerName);
    }

    @Test(priority = 4)
    public void getPoolCreationTimeAndSendMetric() {
        // Get pool creation time
        poolCreationTime = vLBController.getPoolCreationTime(poolName, loadBalancerName);
        // Send metric of pool to vMonitor
        MetricService.sendPoolMetric(poolName, loadBalancerName, poolCreationTime);
        // If pool creation time = 3600 then failed test script
        Assert.assertNotEquals(poolCreationTime, vLBData.WAIT_SECONDS);
    }

    @Test(priority = 5)
    public void createListener() {
        vLBService.createHTTPListenerForLayer7LoadBalancer(listenerName, poolName, loadBalancerName);
    }

    @Test(priority = 6)
    public void getListenerCreationTimeAndSendMetric() {
        // Get listener creation time
        listenerCreationTime = vLBController.getListenerCreationTime(listenerName, loadBalancerName);
        // Send metric of listener to vMonitor
        MetricService.sendListenerMetric(listenerName, loadBalancerName, listenerCreationTime);
        // If pool creation time = 3600 then failed test script
        Assert.assertNotEquals(listenerCreationTime, vLBData.WAIT_SECONDS);
    }

    @Test(priority = 7)
    public void writeToExcelFile() {
        String loadBalancerId = vLBController.getLoadBalancerId(loadBalancerName);
        List<Map<String, Object>> data = List.of(
                Map.of("Load Balancer ID", loadBalancerId, "Load Balancer Name", loadBalancerName, "Pool Name",
                        poolName,
                        "Listener Name", listenerName, "LB Creation Time", loadBalancerCreationTimeSeconds,
                        "Pool Creation Time", poolCreationTime, "Listener Creation Time", listenerCreationTime));

        ExcelController.writeLoadBalancerExcel("load-balancer-creation-time.xlsx", data);
    }

    @Test(priority = 8)
    public void deleteLoadBalancer() {
        if (loadBalancerCreationTimeSeconds != vLBData.WAIT_SECONDS &&
                poolCreationTime != vLBData.WAIT_SECONDS &&
                listenerCreationTime != vLBData.WAIT_SECONDS) {
            vLBService.deleteLoadBalancer(loadBalancerName);
        }
    }
}
