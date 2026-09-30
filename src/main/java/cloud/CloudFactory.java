package cloud;

/** ABSTRACT FACTORY. The type parameter C fixes the family for ALL products it creates. */
public interface CloudFactory<C extends Cloud> {
    Storage<C> createStorage();
    Compute<C> createCompute();
    Database<C> createDatabase();
    BackupJob<C> createBackupJob();
}
