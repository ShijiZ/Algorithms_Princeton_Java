/*
 * Sample usage: java-alg4 Edge
 */

public class Edge implements Comparable<Edge> {
    private final int v;            // one vertex
    private final int w;            // the other vertex
    private final double weight;    // edge weight

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if (vertex == v)
            return w;
        else
            return v;
    }

    public int compareTo(Edge that){
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return 1;
        else return 0;
    }

    public String toString(){
        return String.format("%d-%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
    }
}