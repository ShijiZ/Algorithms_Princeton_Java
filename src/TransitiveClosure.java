/*
 * Sample usage: java TransitiveClosure tinyDG.txt
 * Sample usage: java TransitiveClosure mediumDG.txt
 */

public class TransitiveClosure {
    private DirectedDFS[] all;

    public TransitiveClosure(Digraph G){
        all = new DirectedDFS[G.V()];
        for (int v=0; v<G.V(); v++)
            all[v] = new DirectedDFS(G, v);
    }

    public boolean reachable(int v, int w){
        return all[v].marked(w);
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        TransitiveClosure tc = new TransitiveClosure(G);

        StdOut.print("     ");
        for (int v = 0; v < G.V(); v++)
            StdOut.printf("%3d", v);
        StdOut.println();
        StdOut.println("--------------------------------------------");

        // print transitive closure
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%3d: ", v);
            for (int w = 0; w < G.V(); w++) {
                if (tc.reachable(v, w)) StdOut.print("  T");
                else                    StdOut.print("   ");
            }
            StdOut.println();
        }
    }
}
