package cloud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NegativeScenariosTest {

    @Test
    void unknownFamilyIsRejectedWithHelpfulMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> CloudFactories.create("oracle"));
        assertTrue(e.getMessage().contains("aws"));   // lists available families
    }

    @Test
    void deployRejectsNonPositiveSize() {
        DeploymentService<AwsFactory.Aws> service = new DeploymentService<>(new AwsFactory());
        assertThrows(IllegalArgumentException.class, () -> service.deploy("shop", 0));
    }

    @Test
    void backupJobRejectsNonPositiveSize() {
        assertThrows(IllegalArgumentException.class, () -> new GcpBackupJob().run("d", -5));
    }

    @Test
    void serviceRejectsMissingFactory() {
        assertThrows(NullPointerException.class, () -> new DeploymentService<AwsFactory.Aws>(null));
    }
}
