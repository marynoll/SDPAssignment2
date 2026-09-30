package cloud;

class AzureDatabase implements Database<AzureFactory.Azure> {
    public String label() { return "Azure SQL"; }
    public double monthlyCost(int gb) { return gb * 0.10 + 12.0; }
    public String backupTo(Storage<AzureFactory.Azure> storage, int gb) { return "Azure SQL backup -> " + storage.save("db-snapshot", gb); }
}
