/*
 * Sample usage: java DirectedCycle tinyDG.txt
 * Sample usage: java DirectedCycle mediumDG.txt
 */

public class DirectedCycle {
    private boolean[] marked;           // marked[v] = has vertex v been marked?
    private int[] edgeTo;               // edgeTo[v] = previous vertex on path to v
    private LinkedStack<Integer> cycle; // vertices on a cycle (if one exists)
    private boolean[] onStack;          // vertices on recursive call stack

    public DirectedCycle(Digraph G){
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v=0; v<G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v))
            // short circuit if directed cycle found
            if (this.hasCycle()) return;

            // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]){
                cycle = new LinkedStack<>();
                for (int x=v; x!=w; x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                return;
            }
        onStack[v] = false;
    }

    public boolean marked(int w){
        return marked[w];
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.println("Directed cycle: ");
            for (int v: finder.cycle())
                StdOut.print(v + " -> ");
            StdOut.println();
        }
        else {
            StdOut.println("No directed cycle found");
        }
    }
}
