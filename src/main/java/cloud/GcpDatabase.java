package cloud;

class GcpDatabase implements Database<GcpFactory.Gcp> {
    public String label() { return "GCP Cloud SQL"; }
    public double monthlyCost(int gb) { return gb * 0.17; }
    public String backupTo(Storage<GcpFactory.Gcp> storage, int gb) { return "Cloud SQL backup -> " + storage.save("db-snapshot", gb); }
}
