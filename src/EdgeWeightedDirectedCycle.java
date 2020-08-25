/*
 * Sample usage: java EdgeWeightedDirectedCycle tinyEWD.txt
 * Sample usage: java EdgeWeightedDirectedCycle mediumEWD.txt
 */

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;                // marked[v] = has vertex v been marked?
    private DirectedEdge[] edgeTo;           // edgeTo[v] = previous edge on path to v
    private LinkedStack<DirectedEdge> cycle; // edges on a cycle (if one exists)
    private boolean[] onStack;               // vertices on recursive call stack

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G){
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int v=0; v<G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(EdgeWeightedDigraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            // short circuit if directed cycle found
            if (this.hasCycle()) return;

            // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new LinkedStack<>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean marked(int w){
        return marked[w];
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.println("Directed cycle: ");
            for (DirectedEdge e: finder.cycle())
                StdOut.print(e + " => ");
            StdOut.println();
        }
        else {
            StdOut.println("No directed cycle found");
        }
    }
}
