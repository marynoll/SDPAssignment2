package cloud;

class AwsStorage implements Storage<AwsFactory.Aws> {
    public String label() { return "AWS S3"; }
    public double monthlyCost(int gb) { return gb * 0.023; }
    public String save(String what, int gb) { return "s3://backups/" + what + " (" + gb + " GB)"; }
}
