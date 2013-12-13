import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

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
        int resultado = 2000000000;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < resultado && !visitados.contains(i)) {
                resultado = i;
            }
        }
        return resultado;
    }


    int Compute_Indeg(int node, int n, GrafoNPD g) throws NullPointerException {
        int v1, indeg_count = 0;
        for (v1 = 0; v1 < n; v1++)
            if (g.getA()[v1][node] == 1)  //checking for incoming edge
                indeg_count++;
        return indeg_count++;
    }


    int[] Topo_ordering(GrafoNPD g) {
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(g.orden());
        int[] indegree = new int[g.orden()];
        int n = g.orden();
        int j = 0;
        for (int i = 0; i < n; i++) {
            indegree[i] = Compute_Indeg(i, n, g);

            if (indegree[i] == 0)
                queue.add(i);
        }
        int b[] = new int[g.orden()];
        while (!queue.isEmpty()) {
            int k = queue.poll();
            b[j++] = k;
            for (int i = 0; i < n; i++) {
                if (g.getA()[k][i] == 1) {
                    g.getA()[k][i] = 0;
                    indegree[i] = indegree[i] - 1;
                    if (indegree[i] == 0)
                        queue.add(i);

                }
            }
        }

        return b;
    }


    public GrafoNDP cableado() {

        try (BufferedReader br = new BufferedReader(new FileReader("Aristas.dat"))) {
            String str1 = br.readLine();
            GrafoNDP g = new GrafoNDP(Integer.parseInt(str1));
            for (int i = 0; i < Integer.parseInt(str1); i++) {
                g.agregarVertice("Ciudad " + i);
            }
            str1 = br.readLine();
            while (str1 != null) {
                g.agregarArista(Integer.parseInt(str1.charAt(0) + ""), Integer.parseInt(str1.charAt(1) + ""), Integer.parseInt(str1.charAt(2) + ""));
                str1 = br.readLine();
            }
            return aplicarPrim(g);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public GrafoNDP aplicarPrim(GrafoNDP grafo) {
        GrafoNDP arbol = new GrafoNDP();
        arbol.setV(grafo.getV());
        arbol.setN(grafo.getN());
        PriorityQueue<Arista> L = new PriorityQueue<>(grafo.orden(), new ComparatorAristas());
        for (int i = 0; i < grafo.orden(); i++) {
            for (int j = 0; j < grafo.orden(); j++) {
                if (i != j && grafo.arista(i, j) != 2147483647)
                    L.add(new Arista(i, j, grafo.arista(i, j)));
            }
        }
        List<Integer> incluidos = new ArrayList<Integer>(grafo.orden());
        List<Integer> noIncluidos = new ArrayList<Integer>(grafo.orden());
        for (int i = 0; i < grafo.orden(); i++) {
            noIncluidos.add(i);
        }
        Arista min = L.poll();
        moverArista(arbol, incluidos, noIncluidos, min);
        while (!noIncluidos.isEmpty()) {
            min = L.poll();
            if ((incluidos.contains(min.getV()) && noIncluidos.contains(min.getW()))
                    || (incluidos.contains(min.getW()) && noIncluidos.contains(min.getV()))) {
                moverArista(arbol, incluidos, noIncluidos, min);
            }

        }
        return arbol;
    }

    private void moverArista(GrafoNDP arbol, List<Integer> incluidos, List<Integer> noIncluidos, Arista min) {
        arbol.agregarArista(min.getV(), min.getW(), min.getPeso());
        noIncluidos.remove((Integer) min.getV());
        noIncluidos.remove((Integer) min.getW());
        incluidos.add(min.getV());
        incluidos.add(min.getW());
    }


    public double[][] FloydWarshall(GrafoPD g) {
        double[][] dist = new double[g.orden()][g.orden()];
        for (int i = 0; i < g.orden(); i++) {
            for (int j = 0; j < g.orden(); j++) {
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






