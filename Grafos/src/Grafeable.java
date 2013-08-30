import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 23/08/13
 * Time: 13:43
 */
public interface Grafeable {

    void agregarVertice(Object x);

    void agregarArista(int v, int w);

    void eliminarArista(int v, int w);

    void eliminarVertice(int v);

    boolean hayArista(int v, int w);

    int orden();

    int cantAristas();

    Object verVertice(int v);

    List getListaAdy(int v);
}
