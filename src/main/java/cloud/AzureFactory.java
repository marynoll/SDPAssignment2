package cloud;

/** ConcreteFactory for the Azure family. */
public class AzureFactory implements CloudFactory<AzureFactory.Azure> {

    /** Marker type meaning "this product belongs to the Azure family". Nobody can instantiate it. */
    public static final class Azure implements Cloud { private Azure() {} }

    public Storage<Azure> createStorage()     { return new AzureStorage(); }
    public Compute<Azure> createCompute()     { return new AzureCompute(); }
    public Database<Azure> createDatabase()   { return new AzureDatabase(); }
    public BackupJob<Azure> createBackupJob() { return new AzureBackupJob(); }
}
