/*
 * Sample usage: java Graph tinyG.txt
 * Sample usage: java Graph mediumG.txt
 */

public class Graph {
    private final int V;               // number of vertices
    private int E;                     // number of edges
    private LinkedBag<Integer>[] adj;  // adjacency lists

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (LinkedBag<Integer>[]) new LinkedBag[V];  // Create array of lists
        for (int v = 0; v < V; v++)            // Initialize all lists to empty
            adj[v] = new LinkedBag<>();
    }

    public Graph(In in){
        this(in.readInt());       // read V and construct this graph
        int E = in.readInt();     // read E
        for (int i=0; i<E; i++){
            // Add an edge
            int v = in.readInt(); // read a vertex
            int w = in.readInt(); // read another vertex
            addEdge(v, w);        // add edge connecting them
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);        // add w to v's list
        adj[w].add(v);        // add v to w's list
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v=0; v < V; v++){
            s += v + ": ";
            for (int w : this.adj[v])
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
