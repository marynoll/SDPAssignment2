package cloud;

class AwsCompute implements Compute<AwsFactory.Aws> {
    public String label() { return "AWS EC2"; }
    public double monthlyCost(int hours) { return hours * 0.05; }
    public String mount(Storage<AwsFactory.Aws> storage) { return "EC2 instance attached " + storage.label() + " via IAM role"; }
}
