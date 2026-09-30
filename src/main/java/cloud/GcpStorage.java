package cloud;

class GcpStorage implements Storage<GcpFactory.Gcp> {
    public String label() { return "GCP Cloud Storage"; }
    public double monthlyCost(int gb) { return Math.max(0, gb - 5) * 0.026; }
    public String save(String what, int gb) { return "gs://backups/" + what + " (" + gb + " GB)"; }
}
