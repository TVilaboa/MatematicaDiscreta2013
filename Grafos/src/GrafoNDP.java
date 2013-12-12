/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 12/12/13
 * Time: 18:36
 */
public class GrafoNDP extends Grafo {

    public GrafoNDP() {
        super();
    }

    GrafoNDP(int cant) {

        super(cant);


    }

    public void agregarArista(int v, int w, int peso) {                          //ND y P
        A[v][w] = A[w][v] = peso;

        alfa++;

    }
}
