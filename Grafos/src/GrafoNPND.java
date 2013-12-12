/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 12/12/13
 * Time: 18:34
 */
public class GrafoNPND extends Grafo {

    public void agregarArista(int v, int w) {                     //ND y NP
        A[v][w] = A[w][v] = 1;

        alfa++;

    }
}
