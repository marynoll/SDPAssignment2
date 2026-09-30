package cloud;

/** ConcreteFactory for the Gcp family. */
public class GcpFactory implements CloudFactory<GcpFactory.Gcp> {

    /** Marker type meaning "this product belongs to the Gcp family". Nobody can instantiate it. */
    public static final class Gcp implements Cloud { private Gcp() {} }

    public Storage<Gcp> createStorage()     { return new GcpStorage(); }
    public Compute<Gcp> createCompute()     { return new GcpCompute(); }
    public Database<Gcp> createDatabase()   { return new GcpDatabase(); }
    public BackupJob<Gcp> createBackupJob() { return new GcpBackupJob(); }
}
