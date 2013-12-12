import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 16/08/13
 * Time: 13:20
 */

public abstract class Grafo implements Grafeable {

    protected final int infinito = 2147483640;

    protected Object[] V;

    protected int[][] A;

    protected int n;

    protected int alfa;

    Grafo() {

        V = new Object[10];

        A = new int[10][10];

        n = 0;

        alfa = 0;


    }

    Grafo(int cant) {

        V = new Object[cant];

        A = new int[cant][cant];

        n = 0;

        alfa = 0;


    }

    public Object[] getV() {
        return V;
    }

    public int[][] getA() {
        return A;
    }

    public int getN() {
        return n;
    }

    public int getAlfa() {
        return alfa;
    }

    public void setV(Object[] v) {
        V = v;
    }

    public void setA(int[][] a) {
        A = a;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
    }

    public void agregarVertice(Object x) {


        if (n == V.length)
            duplicar();


        V[n] = x;

        n++;
        for (int i = 0; i <= n; i++) {
            A[n][i] = infinito;
            A[i][n] = infinito;
        }

    }

    protected void duplicar() {
        Object[] vert = new Object[n * 2];
        System.arraycopy(V, 0, vert, 0, n);
        V = vert;
        int[][] aris = new int[n * 2][n * 2];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, aris[i], 0, A[0].length);
        }
        A = aris;
    }


    public void eliminarArista(int v, int w) {

        A[v][w] = A[w][v] = infinito;

        alfa--;

    }

    public void eliminarVertice(int v) {


        Object[] vert = new Object[V.length];
        System.arraycopy(V, 0, vert, 0, v);
        System.arraycopy(V, v + 1, vert, v, V.length - v);
        V = vert;
        //Ahora elimino las adayacencias
        int[][] ady = new int[A.length][A.length];
        for (int i = 0; i < v; i++) {
            System.arraycopy(A, 0, ady, 0, v);
            System.arraycopy(A[i], v + 1, ady[i], v, A[0].length - v);
        }
        for (int i = v + 1; i < A.length; i++) {
            System.arraycopy(A, 0, ady, 0, v);
            System.arraycopy(A[i], v + 1, ady[i], v, A[0].length - v);
        }


    }

    public boolean hayArista(int v, int w) {
        return A[v][w] != infinito;
    }

    public int orden() {
        return n;
    }

    public int cantAristas() {
        return alfa;
    }

    public Object verVertice(int v) {
        return V[v];
    }

    public List<Integer> getListaAdy(int v) {


        List<Integer> list = new ArrayList<>();

        for (int w = 0; w < n; w++)

            if (A[v][w] != infinito)
                list.add(w);
        return list;

    }

    public int arista(int v, int w) {
        return A[v][w];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Grafo{");
        sb.append("V=").append(Arrays.toString(V)).append("\n");
        sb.append("A=\n");
        for (int i = 0; i < n; i++) {
            sb.append(Arrays.toString(A[i]) + "\n");
        }
        return sb.toString();
    }
}

