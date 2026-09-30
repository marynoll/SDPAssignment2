package cloud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class   FamilyCreationTest {

    @Test
    void awsFactoryCreatesAwsProducts() {
        CloudFactory<AwsFactory.Aws> f = new AwsFactory();
        assertInstanceOf(AwsStorage.class, f.createStorage());
        assertInstanceOf(AwsCompute.class, f.createCompute());
        assertInstanceOf(AwsDatabase.class, f.createDatabase());
        assertInstanceOf(AwsBackupJob.class, f.createBackupJob());
    }

    @Test
    void azureFactoryCreatesAzureProducts() {
        CloudFactory<AzureFactory.Azure> f = new AzureFactory();
        assertInstanceOf(AzureStorage.class, f.createStorage());
        assertInstanceOf(AzureCompute.class, f.createCompute());
        assertInstanceOf(AzureDatabase.class, f.createDatabase());
        assertInstanceOf(AzureBackupJob.class, f.createBackupJob());
    }

    @Test
    void gcpFactoryCreatesGcpProducts() {
        CloudFactory<GcpFactory.Gcp> f = new GcpFactory();
        assertInstanceOf(GcpStorage.class, f.createStorage());
        assertInstanceOf(GcpCompute.class, f.createCompute());
        assertInstanceOf(GcpDatabase.class, f.createDatabase());
        assertInstanceOf(GcpBackupJob.class, f.createBackupJob());
    }

    @Test
    void factoryMethodCreatorsChooseTheirOwnStorage() {
        // the same business method run() gives different results because createStorage() differs
        assertTrue(new AwsBackupJob().run("d", 10).contains("s3://"));
        assertTrue(new AzureBackupJob().run("d", 10).contains("azure://"));
        assertTrue(new GcpBackupJob().run("d", 10).contains("gs://"));
    }

    @Test
    void backupJobFromFactoryUsesSameFamilyAsFactoryStorage() {
        assertTrue(new AzureFactory().createBackupJob().run("d", 10)
                .contains(new AzureFactory().createStorage().save("d", 10)));
    }
}
