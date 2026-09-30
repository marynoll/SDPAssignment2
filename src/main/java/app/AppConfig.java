package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public final class AppConfig {
    public static final String DEFAULT_FAMILY = "aws";

    private AppConfig() {}

    public static String familyName(String[] args, Map<String, String> env, Properties file) {
        for (String arg : args) {
            if (arg.startsWith("--family=")) return arg.substring("--family=".length());
        }
        if (env.get("CLOUD_FAMILY") != null) return env.get("CLOUD_FAMILY");
        return file.getProperty("family", DEFAULT_FAMILY);
    }

    public static Properties loadFile(String path) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(path)) {
            props.load(in);
        } catch (IOException e) {
            // no config file: fall back to the default
        }
        return props;
    }
}
