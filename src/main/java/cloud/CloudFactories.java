package cloud;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * The ONLY place that knows the concrete factories.
 * It maps a name ("aws") to a factory, so the rest of the app never writes "new AwsFactory()".
 */
public final class CloudFactories {
    private static final Map<String, Supplier<CloudFactory<?>>> REGISTRY = new LinkedHashMap<>();

    static {
        REGISTRY.put("aws", AwsFactory::new);
        REGISTRY.put("azure", AzureFactory::new);
        REGISTRY.put("gcp", GcpFactory::new);
        REGISTRY.put("onprem", OnPremFactory::new);
    }

    private CloudFactories() {}

    public static CloudFactory<?> create(String family) {
        Supplier<CloudFactory<?>> supplier = REGISTRY.get(family.toLowerCase(Locale.ROOT).trim());
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown family '" + family + "'. Available: " + REGISTRY.keySet());
        }
        return supplier.get();
    }

    public static Set<String> available() {
        return REGISTRY.keySet();
    }
}
