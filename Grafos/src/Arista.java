/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Grafos
 * Date: 10/12/13
 * Time: 19:35
 */
public class Arista implements Comparable<Arista> {

    int v;
    int w;
    int peso;

    public Arista(int v, int w, int peso) {
        this.v = v;
        this.w = w;
        this.peso = peso;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista o) {
        return peso - o.getPeso();
    }
}

