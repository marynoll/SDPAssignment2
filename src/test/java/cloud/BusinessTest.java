package cloud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BusinessTest {

    @Test
    void deployMountsStorageOnComputeAndSavesData() {
        String result = new DeploymentService<>(new AwsFactory()).deploy("shop", 100);
        assertEquals("EC2 instance attached AWS S3 via IAM role; s3://backups/shop-data (100 GB)", result);
    }

    @Test
    void backupCombinesDatabaseSnapshotAndRetentionJob() {
        String result = new DeploymentService<>(new AzureFactory()).backup("orders", 50);
        assertTrue(result.startsWith("Azure SQL backup -> azure://blob/db-snapshot (50 GB)"));
        assertTrue(result.contains("3 copies kept"));
        assertTrue(result.contains("$8.00/month"));   // 150 GB * 0.020 + 5.0 flat
    }

    @Test
    void costEstimateAddsComputeStorageAndDatabase() {
        // AWS: 720*0.05 + 100*0.023 + 50*0.115 = 36 + 2.3 + 5.75
        assertEquals(44.05, new DeploymentService<>(new AwsFactory()).estimateMonthlyCost(720, 100, 50), 0.001);
        assertEquals(57.12, new DeploymentService<>(new AzureFactory()).estimateMonthlyCost(720, 100, 50), 0.001);
    }

    @Test
    void gcpGivesSustainedUseDiscountOnlyForLongRunningServers() {
        Compute<GcpFactory.Gcp> compute = new GcpFactory().createCompute();
        assertEquals(100 * 0.048, compute.monthlyCost(100), 0.001);
        assertEquals(720 * 0.048 * 0.8, compute.monthlyCost(720), 0.001);
    }

    @Test
    void firstFiveGbOfGcpStorageAreFree() {
        assertEquals(0.0, new GcpFactory().createStorage().monthlyCost(5), 0.001);
    }
}
