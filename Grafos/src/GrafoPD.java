/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 12/12/13
 * Time: 18:37
 */
public class GrafoPD extends Grafo {

    public void agregarArista(int v, int w, int peso, boolean direccion) {   //true-> v a w,false -> w a v               //D y P
        if (direccion) A[v][w] = peso;
        else A[w][v] = peso;


        alfa++;

    }
}
