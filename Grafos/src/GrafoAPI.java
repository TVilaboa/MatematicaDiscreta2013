import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 16/08/13
 * Time: 13:20
 */
public class GrafoAPI {

    public int[][] listaAristas(Grafo g) {     //ineficiente para grafos dispersos
        int orden = g.orden();
        int[][] lista = new int[2][g.cantAristas()];
        int contador = 0;
        for (int i = 0; i < orden; i++) {
            for (int j = i + 1; j < orden; j++) {
                if (g.hayArista(i, j)) {
                    lista[1][contador] = i;
                    lista[2][contador] = j;
                    contador++;
                }
            }
        }
        return lista;


    }

    public boolean hayCamino(Grafeable g, int v, int w) {
        boolean[] visitado = new boolean[g.orden()];
        return hayCamino(g, v, w, visitado);
    }

    private boolean hayCamino(Grafeable g, int v, int w, boolean visitado[]) {
        if ((v == w) || g.hayArista(v, w))
            return true;
        visitado[v] = true;
        List<Integer> list = g.getListaAdy(v);
        if (list.isEmpty())
            return false;
        else {
            for (Integer aList : list) {
                if (!visitado[aList] && hayCamino(g, aList, w, visitado)) {
                    return true;
                }

            }
            return false;
        }
    }

    public int[][] listaAristas2(Grafo g) {
        int n = g.orden();
        int[][] a = new int[g.cantAristas()][2];
        int k = -1;
        List<Integer> list;
        for (int i = 0; i < n; i++) {
            list = g.getListaAdy(i);
            if (!list.isEmpty()) {
                for (Integer aList : list) {
                    int w = aList;
                    if (w > i) {
                        k++;
                        a[k][0] = i;
                        a[k][1] = w;
                    }
                }
            }
        }
        return a;
    }
}
