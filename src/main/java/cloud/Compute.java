package cloud;

/** Product type 2: the machine that runs the app. It can only mount Storage of ITS OWN family. */
public interface Compute<C extends Cloud> {
    String label();
    double monthlyCost(int hours);
    String mount(Storage<C> storage);
}
