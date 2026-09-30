package cloud;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class AbstractionTest {

    static final class Fake implements Cloud {}

    static class FakeFactory implements CloudFactory<Fake> {
        public Storage<Fake> createStorage() {
            return new Storage<>() {
                public String label() { return "fake-disk"; }
                public double monthlyCost(int gb) { return 1; }
                public String save(String what, int gb) { return "fake://" + what; }
            };
        }
        public Compute<Fake> createCompute() {
            return new Compute<>() {
                public String label() { return "fake-cpu"; }
                public double monthlyCost(int hours) { return 2; }
                public String mount(Storage<Fake> s) { return "fake-cpu+" + s.label(); }
            };
        }
        public Database<Fake> createDatabase() {
            return new Database<>() {
                public String label() { return "fake-db"; }
                public double monthlyCost(int gb) { return 3; }
                public String backupTo(Storage<Fake> s, int gb) { return "fake-dump " + s.save("db", gb); }
            };
        }
        public BackupJob<Fake> createBackupJob() {
            return new BackupJob<>() {
                protected Storage<Fake> createStorage() { return new FakeFactory().createStorage(); }
            };
        }
    }

    @Test
    void serviceWorksWithAFamilyItHasNeverSeen() {
        DeploymentService<Fake> service = new DeploymentService<>(new FakeFactory());
        assertEquals("fake-cpu+fake-disk; fake://app-data", service.deploy("app", 1));
        assertEquals(6.0, service.estimateMonthlyCost(1, 1, 1), 0.001);
    }

    @Test
    void serviceFieldsAreAbstractTypesOnly() {
        for (Field field : DeploymentService.class.getDeclaredFields()) {
            assertTrue(Modifier.isAbstract(field.getType().getModifiers()),
                    field.getName() + " must be an interface or abstract class");
            assertFalse(field.getType().getSimpleName().matches("(Aws|Azure|Gcp|OnPrem).*"));
        }
    }
}
