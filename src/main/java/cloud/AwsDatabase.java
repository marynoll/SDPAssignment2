package cloud;

class AwsDatabase implements Database<AwsFactory.Aws> {
    public String label() { return "AWS RDS"; }
    public double monthlyCost(int gb) { return gb * 0.115; }
    public String backupTo(Storage<AwsFactory.Aws> storage, int gb) { return "RDS snapshot -> " + storage.save("db-snapshot", gb); }
}
