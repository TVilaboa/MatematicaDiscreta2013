/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 12/12/13
 * Time: 18:36
 */
public class GrafoNPD extends Grafo {

    public void agregarArista(int v, int w, boolean direccion) {       //D y NP
        if (direccion) A[v][w] = 1;
        else A[w][v] = 1;

        alfa++;

    }
}
