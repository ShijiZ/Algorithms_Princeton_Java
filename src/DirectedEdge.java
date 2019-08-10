/*
 * Sample usage: java-alg4 DirectedEdge
 */

public class DirectedEdge {
    private final int v;           // edge source
    private final int w;           // edge target
    private final double weight;   // edge weight

    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public String toString(){
        return String.format("%d->%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }
}
