package cloud;

class OnPremCompute implements Compute<OnPremFactory.OnPrem> {
    public String label() { return "On-Prem Server"; }
    public double monthlyCost(int hours) { return 120.0; }
    public String mount(Storage<OnPremFactory.OnPrem> storage) { return "Physical server mounted " + storage.label() + " over NFS"; }
}
