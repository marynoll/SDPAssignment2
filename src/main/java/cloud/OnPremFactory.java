package cloud;

/** ConcreteFactory for the OnPrem family. */
public class OnPremFactory implements CloudFactory<OnPremFactory.OnPrem> {

    /** Marker type meaning "this product belongs to the OnPrem family". Nobody can instantiate it. */
    public static final class OnPrem implements Cloud { private OnPrem() {} }

    public Storage<OnPrem> createStorage()     { return new OnPremStorage(); }
    public Compute<OnPrem> createCompute()     { return new OnPremCompute(); }
    public Database<OnPrem> createDatabase()   { return new OnPremDatabase(); }
    public BackupJob<OnPrem> createBackupJob() { return new OnPremBackupJob(); }
}
