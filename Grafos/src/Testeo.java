/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 03/12/13
 * Time: 20:04
 */
public class Testeo {

    public static void main(String[] args) {
        GrafoApiFinal api = new GrafoApiFinal();
        GrafoNPND graf = new GrafoNPND();
        for (int i = 0; i < 5; i++) {
            graf.agregarVertice(new Integer(i));
        }
        for (int i = 0; i < 4; i++) {
            graf.agregarArista(i, i + 1);
            graf.agregarArista(0, i);
        }
        graf.agregarArista(2, 5);
        System.out.println(graf);
        api.dijkstra(graf, 0);

    }
}
