package cloud;

/** Product type 1: where data is kept. C = the family this storage belongs to. */
public interface Storage<C extends Cloud> {
    String label();
    double monthlyCost(int gb);
    String save(String what, int gb);
}
