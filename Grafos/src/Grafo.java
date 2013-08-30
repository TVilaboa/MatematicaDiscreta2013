import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 16/08/13
 * Time: 13:20
 */
public class Grafo implements Grafeable{

    private Object[] V;

    private boolean[][] A;

    private int n;

    private int alfa;

    Grafo() {

        V = new Object[10];

        A = new boolean[10][10];

        n = 0;

        alfa = 0;

    }

    Grafo(int capacidad) {

        V = new Object[capacidad];

        A = new boolean[capacidad][capacidad];

        n = 0;

        alfa = 0;

    }

    public void agregarVertice(Object x){

/* Queda a cargo del lector tratar el caso en que haya que

agrandar el arreglo y la matriz*/

        if(n==V.length)
            duplicar();


        V[n] = x;

        n++;

    }

    private void duplicar(){
        Object[] vert=new Object[n*2];
        System.arraycopy(V,0,vert,0,n);
        V=vert;
        boolean[][] aris=new boolean[n*2][n*2];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, aris[i], 0, A[0].length);
        }
        A=aris;
    }

    public void agregarArista(int v, int w){

        A[v][w]=A[w][v] = true;

        alfa++;

    }

    public void eliminarArista(int v, int w){

        A[v][w]=A[w][v] = false;

        alfa--;

    }

    public void eliminarVertice(int v){

      /* Queda a cargo */
        Object[] vert=new Object[V.length];
        System.arraycopy(V,0,vert,0,v);
        System.arraycopy(V,v+1,vert,v,V.length-v);
        V=vert;
        //Ahora elimino las adayacencias
        boolean[][] ady=new boolean[A.length][A.length];
        for (int i = 0; i < v; i++) {
            System.arraycopy(A,0,ady,0,v);
            System.arraycopy(A[i], v+1, ady[i], v, A[0].length -v);
        }
        for (int i = v+1; i < A.length; i++) {
            System.arraycopy(A,0,ady,0,v);
            System.arraycopy(A[i], v+1, ady[i], v, A[0].length -v);
        }


    }

    public boolean hayArista(int v, int w){
        return A[v][w];
    }

    public int orden(){
        return n;
    }

    public int cantAristas(){
        return alfa;
    }

    public Object verVertice(int v){
        return V[v];
    }

    public List<Integer> getListaAdy(int v){



        List<Integer> list=new ArrayList<>();

        for (int w = 0; w < n ; w++)

            if (A[v][w])
        list.add(w);
        return list;

    }

}

