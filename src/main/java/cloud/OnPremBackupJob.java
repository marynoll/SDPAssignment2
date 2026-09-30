package cloud;

/** ConcreteCreator: only decides WHICH storage the backup uses. */
public class OnPremBackupJob extends BackupJob<OnPremFactory.OnPrem> {
    protected Storage<OnPremFactory.OnPrem> createStorage() { return new OnPremStorage(); }
}
