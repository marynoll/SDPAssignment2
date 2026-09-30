package cloud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RuntimeSelectionTest {

    @Test
    void namesSelectTheRightFactory() {
        assertInstanceOf(AwsFactory.class, CloudFactories.create("aws"));
        assertInstanceOf(AzureFactory.class, CloudFactories.create("azure"));
        assertInstanceOf(GcpFactory.class, CloudFactories.create("gcp"));
    }

    @Test
    void selectionIgnoresCaseAndSpaces() {
        assertInstanceOf(AzureFactory.class, CloudFactories.create("  AZURE "));
    }

    @Test
    void sameServiceCodeWorksWithEveryFamilySelectedAtRuntime() {
        for (String name : new String[]{"aws", "azure", "gcp"}) {
            String text = deployWith(CloudFactories.create(name));
            assertTrue(text.contains("attached"), name);
        }
    }

    private static <C extends Cloud> String deployWith(CloudFactory<C> factory) {
        return new DeploymentService<>(factory).deploy("app", 10);
    }
}
