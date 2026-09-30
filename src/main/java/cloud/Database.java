package cloud;

/** Product type 3: the database. It can only back up into Storage of ITS OWN family. */
public interface Database<C extends Cloud> {
    String label();
    double monthlyCost(int gb);
    String backupTo(Storage<C> storage, int gb);
}
