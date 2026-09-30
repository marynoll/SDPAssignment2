package cloud;

class AzureStorage implements Storage<AzureFactory.Azure> {
    public String label() { return "Azure Blob"; }
    public double monthlyCost(int gb) { return gb * 0.020 + 5.0; }
    public String save(String what, int gb) { return "azure://blob/" + what + " (" + gb + " GB)"; }
}
