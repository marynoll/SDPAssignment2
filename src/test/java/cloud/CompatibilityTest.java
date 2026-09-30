package cloud;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;

class CompatibilityTest {

    @Test
    void productsOfOneFactoryWorkTogether() {
        CloudFactory<GcpFactory.Gcp> f = new GcpFactory();
        String text = f.createCompute().mount(f.createStorage());
        assertTrue(text.contains("GCP Cloud Storage"));
    }

    @Test
    void mountParameterTypeEncodesTheFamily() throws Exception {
        String type = AwsCompute.class.getMethod("mount", Storage.class)
                .getGenericParameterTypes()[0].getTypeName();
        assertTrue(type.contains("AwsFactory$Aws"), type);   // Storage<AwsFactory$Aws>
    }

    @Test
    void sameFamilyCombinationCompiles() throws Exception {
        assertTrue(compiles("new AwsFactory().createCompute().mount(new AwsFactory().createStorage());"));
    }

    @Test
    void mixedFamilyCombinationDoesNotCompile() throws Exception {
        assertFalse(compiles("new AwsFactory().createCompute().mount(new AzureFactory().createStorage());"));
        assertFalse(compiles("new GcpFactory().createDatabase().backupTo(new AwsFactory().createStorage(), 1);"));
    }

    /** Compiles a tiny class with the given statement and tells whether javac accepted it. */
    private static boolean compiles(String statement) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        assumeTrue(compiler != null, "a JDK is needed for this test");
        Path dir = Files.createTempDirectory("probe");
        Path file = dir.resolve("Probe.java");
        Files.writeString(file, "package cloud; class Probe { void f() { " + statement + " } }");
        int result = compiler.run(null, null, new ByteArrayOutputStream(), "-proc:none",
                "-cp", System.getProperty("java.class.path"), "-d", dir.toString(), file.toString());
        return result == 0;
    }
}
