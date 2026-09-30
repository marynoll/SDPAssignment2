package cloud;

/** ConcreteFactory for the Aws family. */
public class AwsFactory implements CloudFactory<AwsFactory.Aws> {

    /** Marker type meaning "this product belongs to the Aws family". Nobody can instantiate it. */
    public static final class Aws implements Cloud { private Aws() {} }

    public Storage<Aws> createStorage()     { return new AwsStorage(); }
    public Compute<Aws> createCompute()     { return new AwsCompute(); }
    public Database<Aws> createDatabase()   { return new AwsDatabase(); }
    public BackupJob<Aws> createBackupJob() { return new AwsBackupJob(); }
}
