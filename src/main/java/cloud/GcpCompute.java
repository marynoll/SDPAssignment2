package cloud;

class GcpCompute implements Compute<GcpFactory.Gcp> {
    public String label() { return "GCP Compute Engine"; }
    public double monthlyCost(int hours) { return hours >= 500 ? hours * 0.048 * 0.8 : hours * 0.048; }
    public String mount(Storage<GcpFactory.Gcp> storage) { return "Compute Engine VM attached " + storage.label() + " via service account"; }
}
