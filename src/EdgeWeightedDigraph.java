/*
 * Sample usage: java EdgeWeightedDigraph tinyEWD.txt
 * Sample usage: java EdgeWeightedDigraph mediumEWD.txt
 */

public class EdgeWeightedDigraph {
    private final int V;                       // number of vertices
    private int E;                             // number of edges
    private LinkedBag<DirectedEdge>[] adj;     // adjacency lists

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = (LinkedBag<DirectedEdge>[]) new LinkedBag[V];  // Create array of lists
        for (int v = 0; v < V; v++)            // Initialize all lists to empty
            adj[v] = new LinkedBag<DirectedEdge>();
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());       // read V and construct this graph
        int E = in.readInt();     // read E
        for (int i=0; i<E; i++){
            // Add an edge
            int v = in.readInt();               // read a vertex
            int w = in.readInt();               // read another vertex
            double weight = in.readDouble();    // read weight
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);                         // add edge connecting them
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(DirectedEdge e){
        int v = e.from();
        adj[v].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        LinkedBag<DirectedEdge> b = new LinkedBag<>();
        for (int v=0; v<V; v++)
            for (DirectedEdge e: adj[v])
                b.add(e);
        return b;
    }

    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v=0; v < V; v++){
            s += v + ": ";
            for (DirectedEdge e : this.adj[v])
                s += e + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}
