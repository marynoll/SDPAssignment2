package cloud;

/** ConcreteCreator: only decides WHICH storage the backup uses. */
public class AwsBackupJob extends BackupJob<AwsFactory.Aws> {
    protected Storage<AwsFactory.Aws> createStorage() { return new AwsStorage(); }
}
