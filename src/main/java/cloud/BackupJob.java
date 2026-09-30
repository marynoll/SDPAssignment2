package cloud;

import java.util.Locale;

/**
 * CREATOR (Factory Method pattern).
 * run() is the business logic. It needs a Storage but does NOT know which one:
 * the subclass decides by implementing createStorage() - the FACTORY METHOD.
 */
public abstract class BackupJob<C extends Cloud> {
    static final int COPIES = 3;   // retention policy: keep 3 copies

    /** The factory method. Each ConcreteCreator returns its own kind of Storage. */
    protected abstract Storage<C> createStorage();

    /** Business logic that uses the product. */
    public String run(String dataset, int gb) {
        if (gb <= 0) throw new IllegalArgumentException("size must be positive");
        Storage<C> storage = createStorage();
        String where = storage.save(dataset, gb);
        double cost = storage.monthlyCost(gb * COPIES);
        return String.format(Locale.US, "Backup %s -> %s, %d copies kept, extra cost $%.2f/month",
                dataset, where, COPIES, cost);
    }
}
