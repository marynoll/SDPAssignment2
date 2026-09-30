package cloud;

class AzureCompute implements Compute<AzureFactory.Azure> {
    public String label() { return "Azure VM"; }
    public double monthlyCost(int hours) { return hours * 0.046; }
    public String mount(Storage<AzureFactory.Azure> storage) { return "Azure VM attached " + storage.label() + " via managed identity"; }
}
