package cloud;

/** ConcreteCreator: only decides WHICH storage the backup uses. */
public class GcpBackupJob extends BackupJob<GcpFactory.Gcp> {
    protected Storage<GcpFactory.Gcp> createStorage() { return new GcpStorage(); }
}
