package cloud;

import java.util.Objects;

/**
 * Business logic. It receives a factory and only uses abstractions:
 * Compute, Storage, Database, BackupJob. It has no idea whether this is AWS, Azure or something new.
 * The type parameter C guarantees all four objects come from the same family.
 */
public class DeploymentService<C extends Cloud> {
    private final Compute<C> compute;
    private final Storage<C> storage;
    private final Database<C> database;
    private final BackupJob<C> backupJob;

    public DeploymentService(CloudFactory<C> factory) {
        Objects.requireNonNull(factory, "factory");
        this.compute = factory.createCompute();
        this.storage = factory.createStorage();
        this.database = factory.createDatabase();
        this.backupJob = factory.createBackupJob();
    }

    /** Operation 1: put the app on a server and store its data (Compute + Storage). */
    public String deploy(String app, int dataGb) {
        if (dataGb <= 0) throw new IllegalArgumentException("data size must be positive");
        return compute.mount(storage) + "; " + storage.save(app + "-data", dataGb);
    }

    /** Operation 2: nightly backup = database snapshot + file backup (Database + Storage + BackupJob). */
    public String backup(String dataset, int gb) {
        return database.backupTo(storage, gb) + "\n" + backupJob.run(dataset, gb);
    }

    /** Operation 3: price of the whole stack (Compute + Storage + Database). */
    public double estimateMonthlyCost(int hours, int storageGb, int databaseGb) {
        return compute.monthlyCost(hours) + storage.monthlyCost(storageGb) + database.monthlyCost(databaseGb);
    }
}
