package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

class AppConfigTest {
    private static Properties file(String family) {
        Properties p = new Properties();
        p.setProperty("family", family);
        return p;
    }

    @Test
    void commandLineBeatsEnvironmentAndFile() {
        assertEquals("gcp", AppConfig.familyName(new String[]{"--family=gcp"}, Map.of("CLOUD_FAMILY", "azure"), file("aws")));
    }

    @Test
    void environmentBeatsFile() {
        assertEquals("azure", AppConfig.familyName(new String[]{}, Map.of("CLOUD_FAMILY", "azure"), file("aws")));
    }

    @Test
    void fileIsUsedThenDefault() {
        assertEquals("gcp", AppConfig.familyName(new String[]{}, Map.of(), file("gcp")));
        assertEquals("aws", AppConfig.familyName(new String[]{}, Map.of(), new Properties()));
    }
}
