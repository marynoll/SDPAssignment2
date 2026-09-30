package cloud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FourthFamilyTest {

    @Test
    void onPremFactoryCreatesAllOnPremProducts() {
        CloudFactory<OnPremFactory.OnPrem> f = new OnPremFactory();
        assertInstanceOf(OnPremStorage.class, f.createStorage());
        assertInstanceOf(OnPremCompute.class, f.createCompute());
        assertInstanceOf(OnPremDatabase.class, f.createDatabase());
        assertInstanceOf(OnPremBackupJob.class, f.createBackupJob());
    }

    @Test
    void runtimeSelectionKnowsTheNewFamily() {
        assertInstanceOf(OnPremFactory.class, CloudFactories.create("onprem"));
        assertTrue(CloudFactories.available().contains("onprem"));
    }

    @Test
    void existingBusinessServiceWorksUnchangedWithOnPrem() {
        String text = new DeploymentService<>(new OnPremFactory()).deploy("shop", 100);
        assertEquals("Physical server mounted On-Prem NAS over NFS; nas://datacenter/shop-data (100 GB)", text);
    }

    @Test
    void onPremServerHasFlatPriceWhateverTheHours() {
        Compute<OnPremFactory.OnPrem> c = new OnPremFactory().createCompute();
        assertEquals(c.monthlyCost(1), c.monthlyCost(720), 0.001);
    }

    @Test
    void onPremStaysIncompatibleWithOtherFamilies() throws Exception {
        String type = OnPremCompute.class.getMethod("mount", Storage.class)
                .getGenericParameterTypes()[0].getTypeName();
        assertTrue(type.contains("OnPremFactory$OnPrem"), type);
    }
}
