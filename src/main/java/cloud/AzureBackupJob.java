package cloud;

/** ConcreteCreator: only decides WHICH storage the backup uses. */
public class AzureBackupJob extends BackupJob<AzureFactory.Azure> {
    protected Storage<AzureFactory.Azure> createStorage() { return new AzureStorage(); }
}
