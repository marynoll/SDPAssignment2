package app;

import cloud.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String family = AppConfig.familyName(args, System.getenv(), AppConfig.loadFile("cloud.properties"));
        CloudFactory<?> factory = CloudFactories.create(family);   // the only place a family is chosen
        System.out.println("Family: " + family);
        run(factory);
    }

    /** After this point the code never mentions a concrete family. */
    private static <C extends Cloud> void run(CloudFactory<C> factory) {
        DeploymentService<C> service = new DeploymentService<>(factory);
        System.out.println(service.deploy("shop", 100));
        System.out.println(service.backup("orders", 50));
        System.out.printf(Locale.US, "Estimated monthly cost: $%.2f%n", service.estimateMonthlyCost(720, 100, 50));
    }
}
