/*
 * Sample usage: java Cycle tinyG.txt
 * Sample usage: java Cycle mediumG.txt
 */

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    // We assume there is no self-loop or parallel edges
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for (int s=0; s<G.V(); s++)
            if (!marked[s])
                dfs(G, s, s); // The second s is a dummy variable, which can be set to -1
    }

    private void dfs(Graph G, int v, int u){
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w, v);
            else if (w!=u) hasCycle=true;
    }

    public boolean marked(int w){
        return marked[w];
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            StdOut.println("Graph is cyclic");
        }
        else {
            StdOut.println("Graph is acyclic");
        }
    }
}
