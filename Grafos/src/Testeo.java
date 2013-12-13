import java.util.Arrays;

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
        /*
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


        GrafoNPD g=new GrafoNPD();
        g.agregarVertice("Undershorts"); //0
        g.agregarVertice("Pants");        //1
        g.agregarVertice("Belt");           //2
        g.agregarVertice("Shirt"); //3
        g.agregarVertice("Tie");      //4
        g.agregarVertice("Jacket");      //5
        g.agregarVertice("Socks");   //6
        g.agregarVertice("Shoes");      //7
        g.agregarVertice("Watch");         //8
        g.agregarArista(0,1,true);
        g.agregarArista(0,7,true);
        g.agregarArista(1,7,true);
        g.agregarArista(1,2,true);
        g.agregarArista(2,3,false);
        g.agregarArista(2,5,true);
        g.agregarArista(3,4,true);
        g.agregarArista(4,5,true);
        g.agregarArista(6,7,true);
        System.out.println(g);

        System.out.println("\nThe result of after topological sorting is...");
          int[] b= api.Topo_ordering(g);
        for(int i=0;i<g.orden();i++)
                System.out.print("  "+g.verVertice(b[i]));

        System.out.println(api.cableado());
          */
        GrafoPD grafPD = new GrafoPD();
        grafPD.agregarVertice("Undershorts"); //0
        grafPD.agregarVertice("Pants");        //1
        grafPD.agregarVertice("Belt");           //2
        grafPD.agregarVertice("Shirt"); //3
        grafPD.agregarVertice("Tie");      //4
        grafPD.agregarVertice("Jacket");      //5
        grafPD.agregarVertice("Socks");   //6
        grafPD.agregarVertice("Shoes");      //7
        grafPD.agregarVertice("Watch");         //8
        grafPD.agregarArista(0, 1, 50, true);
        grafPD.agregarArista(0, 7, 25, true);
        grafPD.agregarArista(1, 7, 75, true);
        grafPD.agregarArista(1, 2, 55, true);
        grafPD.agregarArista(2, 3, 22, false);
        grafPD.agregarArista(2, 5, 32, true);
        grafPD.agregarArista(3, 4, 27, true);
        grafPD.agregarArista(4, 5, 68, true);
        grafPD.agregarArista(6, 7, 50, true);
        System.out.println(grafPD);
        double[][] dist = api.FloydWarshall(grafPD);
        final StringBuilder sb = new StringBuilder("Matriz{");
        for (int i = 0; i < dist.length; i++) {
            sb.append(Arrays.toString(dist[i]) + "\n");
        }
        System.out.println(sb.toString());

    }


}
