package cloud;

class OnPremDatabase implements Database<OnPremFactory.OnPrem> {
    public String label() { return "On-Prem PostgreSQL"; }
    public double monthlyCost(int gb) { return 80.0 + gb * 0.02; }
    public String backupTo(Storage<OnPremFactory.OnPrem> storage, int gb) { return "pg_dump -> " + storage.save("db-snapshot", gb); }
}
