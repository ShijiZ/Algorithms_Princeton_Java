/*
 * Sample usage: java-alg4 Topological jobs.txt "/"
 */

public class Topological {
    private Iterable<Integer> order;       // topological order

    public Topological(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order==null;
    }

    public static void main(String[] args){
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        Topological top = new Topological(sg.G());

        for (int v: top.order())
            StdOut.println(sg.name(v));

    }
}
