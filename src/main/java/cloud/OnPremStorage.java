package cloud;

class OnPremStorage implements Storage<OnPremFactory.OnPrem> {
    public String label() { return "On-Prem NAS"; }
    public double monthlyCost(int gb) { return 50.0 + gb * 0.01; }
    public String save(String what, int gb) { return "nas://datacenter/" + what + " (" + gb + " GB)"; }
}
