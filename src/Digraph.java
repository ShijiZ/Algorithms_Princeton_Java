/*
 * Sample usage: java Graph tinyDG.txt
 * Sample usage: java Graph mediumDG.txt
 */

public class Digraph {
    private final int V;               // number of vertices
    private int E;                     // number of edges
    private LinkedBag<Integer>[] adj;  // adjacency lists

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (LinkedBag<Integer>[]) new LinkedBag[V];  // Create array of lists
        for (int v = 0; v < V; v++)            // Initialize all lists to empty
            adj[v] = new LinkedBag<Integer>();
    }

    public Digraph(In in){
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

    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v=0; v<V; v++)
            for (int w: adj(v))
                R.addEdge(w, v);
            return R;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
