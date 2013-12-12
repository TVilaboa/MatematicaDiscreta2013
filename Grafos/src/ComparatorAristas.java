import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 10/12/13
 * Time: 19:43
 */
public class ComparatorAristas implements Comparator<Arista> {

    @Override
    public int compare(Arista o1, Arista o2) {
        return o1.compareTo(o2);
    }
}
