import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 03/12/13
 * Time: 20:49
 */
public class GrafoApiFinal {

    public void dijkstra(GrafoNPND g, int v) {
        int[] caminos = new int[g.orden()];
        for (int i = 0; i < caminos.length; i++) {
            if (i == v) caminos[i] = 0;
            else {
                caminos[i] = g.arista(v, i);
            }
        }
        int[] modif = new int[g.orden()];
        for (int i = 0; i < modif.length; i++) {
            modif[i] = v;
        }
        List<Integer> visitados = new ArrayList<Integer>();
        visitados.add(v);
        for (int i = 0; i < modif.length - 1; i++) {
            int menor = calcularMenor(caminos, visitados);
            visitados.add(menor);
            for (int j = 0; j < modif.length; j++) {
                System.out.println(g.arista(menor, j));
                if (!visitados.contains(j) && caminos[j] > (caminos[menor] + g.arista(menor, j))) {
                    caminos[j] = caminos[menor] + g.arista(menor, j);
                    modif[j] = menor;
                }

            }
        }
        System.out.println(Arrays.toString(caminos));
        for (int i = 0; i < modif.length; i++) {
            System.out.println("<" + buscarCamino(modif, v, i) + ">");
        }
    }

    private String buscarCamino(int[] modif, int v, int i) {
        if (v == i) return i + "";
        else
            return buscarCamino(modif, v, modif[i]) + "," + i;
    }

    private int calcularMenor(int[] array, List<Integer> visitados) {
        int resultado = 2147483640;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < resultado && !visitados.contains(i)) {
                resultado = i;
            }
        }
        return resultado;
    }

    public void DepthFirstSearch(GrafoNPD g, int s) {
        boolean[] marked = new boolean[g.orden()];
        Stack stack = new Stack();
        dfs(g, s, marked, stack);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    // depth first search from v
    private void dfs(GrafoNPD g, int v, boolean[] marked, Stack stack) {

        marked[v] = true;
        stack.push(g.verVertice(v));
        for (int w : g.getListaAdy(v)) {
            if (!marked[w]) {
                dfs(g, w, marked, stack);
            }
        }
    }

    public void cableado() {

        try (BufferedReader br = new BufferedReader(new FileReader("Aristas.dat"))) {
            String str1 = br.readLine();
            GrafoNDP g = new GrafoNDP(Integer.parseInt(str1));
            for (int i = 0; i < Integer.parseInt(str1); i++) {
                g.agregarVertice("Ciudad " + i);
            }
            while (str1 != null) {
                str1 = br.readLine();
                g.agregarArista(str1.charAt(0), str1.charAt(1), str1.charAt(2));
            }
            aplicarPrim(g);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public GrafoNDP aplicarPrim(GrafoNDP grafo) {
        GrafoNDP arbol = new GrafoNDP();
        arbol.setV(grafo.getV());
        PriorityQueue<Arista> L = new PriorityQueue<>(grafo.orden(), new ComparatorAristas());
        for (int i = 0; i < grafo.orden(); i++) {
            for (int j = 0; j < grafo.orden(); j++) {
                if (i != j && grafo.arista(i, j) != 2147483647)
                    L.add(new Arista(i, j, grafo.arista(i, j)));
            }
        }
        List incluidos = new ArrayList(grafo.orden());
        List noIncluidos = new ArrayList(grafo.orden());
        for (int i = 0; i < grafo.orden(); i++) {
            noIncluidos.add(i);
        }
        Arista min = L.poll();
        moverArista(arbol, incluidos, noIncluidos, min);
        while (incluidos.size() != grafo.orden()) {
            min = L.poll();
            if ((incluidos.contains(min.getV()) && noIncluidos.contains(min.getW()))
                    || (incluidos.contains(min.getW()) && noIncluidos.contains(min.getV()))) {
                moverArista(arbol, incluidos, noIncluidos, min);
            }

        }
        return arbol;
    }

    private void moverArista(GrafoNDP arbol, List incluidos, List noIncluidos, Arista min) {
        arbol.agregarArista(min.getV(), min.getW(), min.getPeso());
        noIncluidos.remove(min.getV());
        noIncluidos.remove(min.getW());
        incluidos.add(min.getV());
        incluidos.add(min.getW());
    }


    public int[][] FloydWarshall(GrafoPD g) {
        int[][] dist = new int[g.orden()][g.orden()];
        for (int i = 0; i < g.orden(); i++) {
            for (int j = 0; i < g.orden(); i++) {
                if (i != j) {
                    dist[i][j] = g.arista(i, j);
                }
            }
        }
        for (int k = 0; k < g.orden(); ++k) {
            for (int i = 0; i < g.orden(); ++i) {
                for (int j = 0; j < g.orden(); ++j) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        return dist;
    }
}






