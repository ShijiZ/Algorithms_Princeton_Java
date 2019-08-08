/*
 * Sample usage: java-alg4 EdgeWeightedGraph tinyEWG.txt
 * Sample usage: java-alg4 EdgeWeightedGraph mediumEWG.txt
 */

public class EdgeWeightedGraph {
    private final int V;               // number of vertices
    private int E;                     // number of edges
    private LinkedBag<Edge>[] adj;     // adjacency lists

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (LinkedBag<Edge>[]) new LinkedBag[V];  // Create array of lists
        for (int v = 0; v < V; v++)            // Initialize all lists to empty
            adj[v] = new LinkedBag<Edge>();
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());       // read V and construct this graph
        int E = in.readInt();     // read E
        for (int i=0; i<E; i++){
            // Add an edge
            int v = in.readInt();               // read a vertex
            int w = in.readInt();               // read another vertex
            double weight = in.readDouble();    // read weight
            Edge e = new Edge(v, w, weight);
            addEdge(e);                         // add edge connecting them
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);        // add w to v's list
        adj[w].add(e);        // add v to w's list
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        LinkedBag<Edge> b = new LinkedBag<>();
        for (int v=0; v<V; v++)
            for (Edge e: adj[v])
                if (e.other(v) > v)
                    b.add(e);
        return b;
    }

    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v=0; v < V; v++){
            s += v + ": ";
            for (Edge e : this.adj[v])
                s += e + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }
}
